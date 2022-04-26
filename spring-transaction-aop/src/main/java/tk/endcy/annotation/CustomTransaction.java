package tk.endcy.annotation;

import java.lang.annotation.*;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 17:51
 */
//定义注解用在方法上
@Target(ElementType.METHOD)
//运行时注解
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomTransaction {
    String value() default "";
}
