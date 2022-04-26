package tk.endcy.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 16:32
 */
@Component
public class TransactionUtils {
    //获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin() {
        return dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
    }


    //提交事务
    public void commit(TransactionStatus transaction) {
        dataSourceTransactionManager.commit(transaction);
    }

    //回滚事务
    public void rollback(TransactionStatus transaction) {
        dataSourceTransactionManager.rollback(transaction);
    }
}
