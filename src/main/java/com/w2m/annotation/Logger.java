package com.w2m.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    @Around("execution(* *(..)) && @annotation(com.w2m.annotation.Timed)")
    public Object logRunningTime(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        logger.info("className={}, methodName={}, totalRunningTimeInMs={},threadId={}",
                ((MethodSignature) point.getSignature()).getDeclaringTypeName(),
                ((MethodSignature) point.getSignature()).getMethod().getName(),
                System.currentTimeMillis() - start,
                Thread.currentThread().getId());
        return result;
    }
}
