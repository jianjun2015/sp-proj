package com.sp.spproj.design.pattern.dynamic.statics;

/**
 * @description:
 * @author: wangjianjun
 * @date:2020-10-11 20:51
 */
public class IndexDao implements Dao {
    @Override
    public void query() {
        System.out.println("IndexDao");
    }
}
