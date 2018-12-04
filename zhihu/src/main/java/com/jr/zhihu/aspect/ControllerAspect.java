package com.jr.zhihu.aspect;

import com.jr.zhihu.bean.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
    author:JR
    通过AOP，实现Controller资源统一返回
 */
@Aspect
@Component
public class ControllerAspect {
    private final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Around(value = "execution(* com.jr.zhihu.controller.*.*(..))")
    public ResultBean aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("对象名"+this.getClass().getName()+"对象地址：" + this.hashCode() + "线程ID：" + Thread.currentThread().getId());
        long startTime = System.currentTimeMillis();
        ResultBean resultBean = null;
        try {
            resultBean = (ResultBean) proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            logger.info(proceedingJoinPoint.getSignature() + "  use time:    " + (System.currentTimeMillis() - startTime)+"mm");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            resultBean = handlerException(throwable);
        }
        return resultBean;
    }

    private ResultBean handlerException(Throwable throwable) {
        ResultBean resultBean = new ResultBean(throwable);
        return resultBean;
    }
}
