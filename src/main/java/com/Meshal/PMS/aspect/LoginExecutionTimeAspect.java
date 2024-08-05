package com.Meshal.PMS.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoginExecutionTimeAspect {

    @Around("execution(* com.Meshal.PMS.service.LoginService.login(..))")
    public Object trackExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Method '" + joinPoint.getSignature().getName() + "' took " + (endTime - startTime) + " ms to execute.");
        return result;
    }
}