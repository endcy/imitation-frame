package tk.endcy.framework.annotation;

import java.lang.annotation.*;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 11:27
 * 功能待实现
 */
@Target(ElementType.PARAMETER)  //参数的注解
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {
    /**
     * 表示参数的别名，必填
     *
     * @return
     */
    String value();
}
