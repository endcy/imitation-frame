package tk.endcy.framework.servlet;

import tk.endcy.framework.annotation.MyController;
import tk.endcy.framework.annotation.MyRequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 11:28
 * @Document: https://blog.csdn.net/romantic112/article/details/80537462
 */
public class MyDispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 5444203311712077566L;
    //spring配置文件里的对象
    private final Properties properties = new Properties();
    // handlerMapping对象，存储url和方法的映射关系
    private final Map<String, Method> handlerMapping = new HashMap<>();
    //扫描路径下的类名列表
    private final List<String> classNames = new ArrayList<>();
    //IOC容器对象,保存注入的实例对象
    private final Map<String, Object> ioc = new HashMap<>();
    //保存url和注入的bean
    private final Map<String, Object> controllerMap = new HashMap<>();

    //重写父类doGet方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    //重写父类doGet方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //处理请求
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500!! Server Exception");
        }
    }


    //重写父类init方法
    @Override
    public void init(ServletConfig config) throws ServletException {
        //1、加载spring的配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2、初始化所有相关联的类,扫描用户设定的包下面所有的类
        doScanner(properties.getProperty("scanPackage"));

        //3.拿到扫描到的类,通过反射机制,实例化,并且放到ioc容器中(k-v  beanName-bean) beanName默认是首字母小写
        doInstance();

        //4.初始化HandlerMapping(将url和method对应上)
        initHandlerMapping();

        System.out.println("------------------spring servlet容器init success -------");
    }

    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        try {
            for (Entry<String, Object> entry : ioc.entrySet()) {
                //获取ioc中注入的bean
                Class<? extends Object> clazz = entry.getValue().getClass();
                if (!clazz.isAnnotationPresent(MyController.class)) {
                    continue;
                }

                //拼url时,是controller头的url拼上方法上的url
                String baseUrl = "";
                if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping annotation = clazz.getAnnotation(MyRequestMapping.class);
                    baseUrl = annotation.value();
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                        continue;
                    }
                    MyRequestMapping annotation = method.getAnnotation(MyRequestMapping.class);
                    String url = annotation.value();

                    url = (baseUrl + "/" + url).replaceAll("/+", "/");
                    handlerMapping.put(url, method);
                    controllerMap.put(url, clazz.newInstance());
                    System.out.println(url + "," + method);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("------------------初始化 HandlerMapping  success -------");
    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        for (String className : classNames) {
            try {
                //把类搞出来,反射来实例化(只有加@MyController需要实例化)
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    ioc.put(toLowerFirstWord(clazz.getSimpleName()), clazz.newInstance());
                } else {
                    continue;
                }


            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        System.out.println("------------------反射完成对象的实例化并且注入到IOC容器中   success -------");
    }

    /**
     * 把字符串的首字母小写
     *
     * @param name
     * @return
     */
    private String toLowerFirstWord(String name) {
        char[] charArray = name.toCharArray();
        charArray[0] += 32;     //大小写正好间隔32的字节
        return String.valueOf(charArray);
    }

    private void doScanner(String packageName) {
        //把所有的.替换成/
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                //递归读取包
                doScanner(packageName + "." + file.getName());
            } else {
                String className = packageName + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }
        System.out.println("------------------扫描spring指定路径的类完成--------------");
    }

    private void doLoadConfig(String contextConfigLocation) {
        //把web.xml中的contextConfigLocation对应value值的文件加载到流里面
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            //用Properties文件加载文件里的内容
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关流
            if (null != resourceAsStream) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("------------------spring contextConfigLocation 配置文件加载完成--------------");
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (handlerMapping.isEmpty()) {
            return;
        }

        String url = req.getRequestURI(); //获取请求的uRL
        String contextPath = req.getContextPath();
        //去除URL中多余的"/"
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        //如果访问的URL不存在，则直接返回404错误
        if (!this.handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 NOT FOUND!");
            return;
        }

        Method method = this.handlerMapping.get(url);
        //获取方法的参数列表
        Class<?>[] parameterTypes = method.getParameterTypes();

        //获取请求的参数
        Map<String, String[]> parameterMap = req.getParameterMap();

        //保存参数值
        Object[] paramValues = new Object[parameterTypes.length];

        //方法的参数列表
        for (int i = 0; i < parameterTypes.length; i++) {
            //根据参数名称，做某些处理
            String requestParam = parameterTypes[i].getSimpleName();


            if (requestParam.equals("HttpServletRequest")) {
                //参数类型已明确，这边强转类型
                paramValues[i] = req;
                continue;
            }
            if (requestParam.equals("HttpServletResponse")) {
                paramValues[i] = resp;
                continue;
            }
            //这里处理传入的参数
            if (requestParam.equals("String")) {
                for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
                    String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                    paramValues[i] = value;
                }
            }
        }
        //利用反射机制来调用
        try {
            //第一个参数是method所对应的实例 在ioc容器中
            method.invoke(this.controllerMap.get(url), paramValues);
            //还可以自定义视图解析器进行数据渲染 ResourceViewResolver 如将数据传入jsp页面等
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
