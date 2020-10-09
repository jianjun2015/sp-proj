package com.sp.spproj.spring.source;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 功能描述:
 *
 * @author: Jianjun
 * @date: 2020-10-09 15:03
 * @version:
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        FirstDemo firstDemo = applicationContext.getBean(FirstDemo.class);
        System.out.println(firstDemo);
    }
}
