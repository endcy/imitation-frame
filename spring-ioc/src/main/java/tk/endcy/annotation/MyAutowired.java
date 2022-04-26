package tk.endcy.annotation;

import java.lang.annotation.*;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 14:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface MyAutowired {
    String value() default "";
}
