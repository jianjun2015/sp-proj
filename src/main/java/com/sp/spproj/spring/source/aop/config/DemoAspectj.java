package com.sp.spproj.spring.source.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @description: Aspectj 需要引用对应的jar    Component需要交给spring 管理
 * @author: wangjianjun
 * @date:2020-10-11 18:25
 */

@Aspect //标注增强处理类（切面类）
@Component //交由Spring容器管理
public class DemoAspectj {

    //切点

    /**
     * 可以到 方法维度
     */
    @Pointcut("execution(public * com.sp.spproj.spring.source.aop.dao.*.*(..))")
    public void pointCut(){}

    /**
     * class 维度
     */
    @Pointcut("within(com.sp.spproj.spring.source.aop.dao.*)")
    public void pointCutWithin(){}

    /**
     * 仅作用方法
     */
    @Pointcut("args(java.lang.String)")
    public void pointCutArgs(){}

    /**
     * 仅作用注解
     */
    @Pointcut("@annotation(com.sp.spproj.spring.source.aop.annotation.IndexAnno)")
    public void pointCutAnnotation(){}

    /**
     * 仅作用 代理类 可以作用于 cglib代理类  但是不能作用于 jdklib
     */
    @Pointcut("this(com.sp.spproj.spring.source.aop.dao.IndexDao2)")
    public void pointCutThis(){}

    /**
     * 仅作用 目标类
     */
//    @Pointcut("target(com.sp.spproj.spring.source.aop.dao.IndexDao2)")
    public void pointCutTarget(){}

//    @Before("pointCutThis()")
    public void pointCutThisAdvisor(){
        System.out.println("before  this Advisor");
    }

//    @Before("pointCutTarget()")
    public void pointCutTargetAdvisor(){
        System.out.println("before target Advisor");
    }

    @Before("pointCut()()")
    public void before(){
        System.out.println("before");
    }

    //通知

    //@After("pointCut()")
    public void after(JoinPoint joinPoint){
        for (Object arg : joinPoint.getArgs()) {

            //参数获取
            System.out.println("after:"+arg.toString());
        }

        System.out.println("after:"+joinPoint.getSignature().getName());
    }

    /**
     * 环绕通知 增强处理
     * @param point
     * @throws Throwable
     */
//    @Around("pointCutArgs()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("around:start");
        point.proceed();
        System.out.println("around:end");

    }

//    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }
}
