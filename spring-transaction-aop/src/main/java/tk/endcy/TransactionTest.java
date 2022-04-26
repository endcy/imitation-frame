package tk.endcy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tk.endcy.config.SpringConfig;
import tk.endcy.service.UserService;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 16:39
 */
public class TransactionTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = (UserService) context.getBean("userServiceImpl");
        //编程式事务测试
//        userService.add();
        //aop声明式事务测试
//        userService.aopAdd();
        //aop注解声明式事务测试
        userService.aopAnnoAdd();
    }
}
