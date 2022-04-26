package tk.endcy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 16:43
 */

@Configuration //通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
@ComponentScan(basePackages = "tk.endcy") //配置扫描包
public class SpringConfig {

}
