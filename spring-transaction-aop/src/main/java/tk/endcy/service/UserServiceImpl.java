package tk.endcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import tk.endcy.annotation.CustomTransaction;
import tk.endcy.dao.UserDAO;
import tk.endcy.transaction.TransactionUtils;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 16:19
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private TransactionUtils transactionUtils;


    @Override
    public void add() {
        TransactionStatus transactionStatus = null;
        try {
            //开启编程式事务
            transactionStatus = transactionUtils.begin();
            userDao.save("test001");

//        throwException();

            userDao.save("test002");
            //提交事务
            if (transactionStatus != null) {
                transactionUtils.commit(transactionStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            if (transactionStatus != null) {
                transactionUtils.rollback(transactionStatus);
            }
        }
    }

    @Override
    public void aopAdd() {
        userDao.save("test003");
//        throwException();
        userDao.save("test004");
    }

    @Override
    @CustomTransaction
    public void aopAnnoAdd() {
        userDao.save("test005");
        throwException();
        userDao.save("test006");
    }

    /**
     * 模拟异常
     */
    private void throwException() {
        System.out.println("中间的间隔，且出现异常");
        int i = 1 / 0;
    }

}
