package com.sp.spproj.design.pattern.dynamic.statics;

/**
 * @description:
 * @author: wangjianjun
 * @date:2020-10-24 12:20
 */
public class MainClass {

    public static void main(String[] args) {

        IndexDao target = new IndexDao();
        IndexDaoProxy indexDaoProxy = new IndexDaoProxy(target);

        indexDaoProxy.query();
    }
}
