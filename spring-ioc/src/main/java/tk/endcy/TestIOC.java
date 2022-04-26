package tk.endcy;

import tk.endcy.bean.UserService;
import tk.endcy.core.MyApplicationContext;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 14:50
 * @Document: https://zhuanlan.zhihu.com/p/58622371
 */
public class TestIOC {
    public static void main(String[] args) throws Exception {
        MyApplicationContext myApplicationContext = new MyApplicationContext();
        UserService userService = (UserService) myApplicationContext.getBean("userService");
        userService.findUser("endcy");
    }
}
