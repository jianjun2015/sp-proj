package com.sp.spproj.design.pattern.dynamic.jdk;

import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: wangjianjun
 * @date:2020-10-11 20:53
 */
public class MainClass {

    public static void main(String[] args) {

        Dao dao = (Dao) Proxy.newProxyInstance(MainClass.class.getClassLoader(), new Class[]{Dao.class}, new IndexInvocationHandler(new IndexDao()));
        dao.query();
    }
}
