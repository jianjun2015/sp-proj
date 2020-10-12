package com.sp.spproj.spring.source.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description:  EnableAspectJAutoProxy 开启Aspect
 * @author: wangjianjun
 * @date:2020-10-11 18:11
 */
@Configuration
@ComponentScan(basePackages = "com.sp.spproj.spring.source.aop")
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class AppConfig {


}
