package tk.endcy.annotation;

import java.lang.annotation.*;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 14:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyComponent {
    String value() default "";
}
