package tk.endcy.framework.annotation;

import java.lang.annotation.*;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 11:25
 */

@Target(ElementType.TYPE)   //注解在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {
    /**
     * 表示给controller注册别名
     *
     * @return
     */
    String value() default "";

}
