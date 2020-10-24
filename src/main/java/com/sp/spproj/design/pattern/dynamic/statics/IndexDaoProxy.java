package com.sp.spproj.design.pattern.dynamic.statics;

/**
 * @description: 静态代理 和目标对应都需要实现同一个接口
 * @author: wangjianjun
 * @date:2020-10-11 20:51
 */
public class IndexDaoProxy implements Dao {

    private IndexDao indexDao;

    public IndexDaoProxy(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    @Override
    public void query() {
        indexDao.query();
        System.out.println("proxy");
    }
}
