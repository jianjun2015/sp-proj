package com.sp.spproj.spring.source.ioc;

import com.sp.spproj.spring.source.base.FirstDemo;
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
        //实现自己的容器 指定初始化参数
//        DemoApplicationContext applicationContext = new DemoApplicationContext(MainConfig.class);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        FirstDemo firstDemo = applicationContext.getBean(FirstDemo.class);
        System.out.println(firstDemo);
    }
}
