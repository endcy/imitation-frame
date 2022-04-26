package tk.endcy.bean;

import tk.endcy.annotation.MyAutowired;
import tk.endcy.annotation.MyComponent;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 14:44
 */
@MyComponent
public class UserService {
    @MyAutowired
    private UserDao userDao;

    public void findUser(String userName) {
        userDao.findUser(userName);
    }

}
