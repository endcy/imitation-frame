package tk.endcy.framework.annotation;

import java.lang.annotation.*;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 11:27
 */

@Target({ElementType.TYPE, ElementType.METHOD})  //注解可以在类或者方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {
    /**
     * 表示访问的url
     *
     * @return
     */
    String value() default "";
}
