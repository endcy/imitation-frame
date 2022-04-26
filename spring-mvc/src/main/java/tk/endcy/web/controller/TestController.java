package tk.endcy.web.controller;

import tk.endcy.framework.annotation.MyController;
import tk.endcy.framework.annotation.MyRequestMapping;
import tk.endcy.framework.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 11:37
 * @Document: https://blog.csdn.net/romantic112/article/details/80537462  参考自定义一个Spring mvc
 */
@MyController
@MyRequestMapping("/test")
public class TestController {
    @MyRequestMapping("/doTest")
    public void test1(HttpServletRequest request, HttpServletResponse response,
                      @MyRequestParam("name") String name) {
        System.out.println(name);
        try {
            response.getWriter().write("doTest method success! param:" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/doTest1")
    public void test2(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("doTest1 method success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
