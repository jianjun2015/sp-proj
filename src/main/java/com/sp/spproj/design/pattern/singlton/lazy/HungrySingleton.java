package com.sp.spproj.design.pattern.singlton.lazy;

/**
 * 功能描述: 饿汉模式
 *
 * 线程安全-类只被初始化加载一次
 *
 * @author: Jianjun
 * @date: 2020-10-20 16:08
 * @version:
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){};

    public HungrySingleton getInstance(){
        return instance;
    }
}
