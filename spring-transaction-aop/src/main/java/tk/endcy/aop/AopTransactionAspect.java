package tk.endcy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.endcy.transaction.TransactionUtils;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 16:31
 */
@Component
@Aspect
public class AopTransactionAspect {

    @Autowired
    private TransactionUtils transactionUtils;

    @Pointcut("@annotation(tk.endcy.annotation.CustomTransaction)")
    public void transactionPointCut() {
    }

    @AfterThrowing(pointcut = "transactionPointCut()")
    public void afterThrowingAnno() {
        afterThrowing();
    }

    //异常通知
    @AfterThrowing("execution(* tk.endcy.service.UserServiceImpl.aopAdd(..))")
    public void afterThrowing() {
        System.out.println("程序已经回滚");
        //获取程序当前事务 进行回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }


    @Around("transactionPointCut()")
    public void aroundAnno(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        around(proceedingJoinPoint);
    }

    // 环绕通知
    @Around("execution(* tk.endcy.service.UserServiceImpl.aopAdd(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("开启事务");
        TransactionStatus begin = transactionUtils.begin();
        proceedingJoinPoint.proceed();
        transactionUtils.commit(begin);
        System.out.println("提交事务");
    }
}
