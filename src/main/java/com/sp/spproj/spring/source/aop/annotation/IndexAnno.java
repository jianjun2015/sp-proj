package com.sp.spproj.spring.source.aop.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @author: wangjianjun
 * @date:2020-10-12 09:30
 */
// 声明注解的保留期限
@Retention(RetentionPolicy.RUNTIME)
// 声明可以使用该注解的目标类型
@Target(ElementType.METHOD)
@Documented
public @interface IndexAnno {

    boolean value() default false;
}
