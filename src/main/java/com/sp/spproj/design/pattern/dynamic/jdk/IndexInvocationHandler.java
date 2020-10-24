package com.sp.spproj.design.pattern.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: wangjianjun
 * @date:2020-10-11 20:52
 */
public class IndexInvocationHandler implements InvocationHandler {

    Dao dao;

    public IndexInvocationHandler(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk");
        dao.query();
        return null;
    }
}
