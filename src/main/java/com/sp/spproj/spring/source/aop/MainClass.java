package com.sp.spproj.spring.source.aop;

import com.sp.spproj.spring.source.aop.config.AppConfig;
import com.sp.spproj.spring.source.aop.dao.Dao;
import com.sp.spproj.spring.source.aop.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: wangjianjun
 * @date:2020-10-11 18:03
 */
public class MainClass {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //applicationContext.start();

        //JDK 代理需要使用 interface
        IndexDao indexDao = applicationContext.getBean(IndexDao.class);
//        Dao indexDao = applicationContext.getBean(Dao.class);
//        Dao indexDao = (Dao) applicationContext.getBean("indexDao2");

        //cglib 可以直接使用类对象
        //IndexDao indexDao = applicationContext.getBean(IndexDao.class);
//        indexDao.query("xx");

        //注解 切面
        indexDao.query2();

//        Dao indexDao2 = (Dao) applicationContext.getBean("indexDao2");
//        indexDao2.query("xx2");
    }
}
