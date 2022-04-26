package tk.endcy.bean;

import tk.endcy.annotation.MyComponent;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 14:43
 */
@MyComponent
public class UserDao {
    public void findUser(String userName) {
        System.out.println("UserDao get userName:" + userName);
    }
}
