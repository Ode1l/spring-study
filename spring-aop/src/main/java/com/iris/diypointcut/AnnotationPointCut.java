package com.iris.diypointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // 标记为切面
public class AnnotationPointCut {
    @Before("execution(* com.iris.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("before");
    }

    @After("execution(* com.iris.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("after");
    }

    @Around("execution(* com.iris.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("before around");
        Signature signature = proceedingJoinPoint.getSignature();
        System.out.println(signature);
        System.out.println(signature.toString());
        System.out.println(signature.getName());
        System.out.println(signature.getDeclaringTypeName());
        System.out.println(signature.getDeclaringType());
        proceedingJoinPoint.proceed();
        System.out.println("after around");
    }
}
