package tk.endcy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 16:27
 */
@Component
@Aspect
public class AopLogAspect {
    //aop 编程里面有几个通知： 前置通知 后置通知，运行通知，异常通知，环绕通知

    // 前置通知
    @Before("execution(* tk.endcy.service.UserServiceImpl.*(..))")
    public void begin() {
        System.out.println("前置通知");
    }

    //
    // 后置通知
    @After("execution(* tk.endcy.service.UserServiceImpl.*(..))")
    public void commit() {
        System.out.println("后置通知");
    }

    // 运行通知
    @AfterReturning("execution(* tk.endcy.service.UserServiceImpl.*(..))")
    public void returning() {
        System.out.println("运行通知");
    }

    // 异常通知
    @AfterThrowing("execution(* tk.endcy.service.UserServiceImpl.*(..))")
    public void afterThrowing() {
        System.out.println("异常通知");
    }

    // 环绕通知
    @Around("execution(* tk.endcy.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知开始");
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知结束");
    }
}
