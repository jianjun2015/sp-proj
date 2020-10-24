package com.sp.spproj.design.pattern.singlton.lazy;

/**
 * 功能描述:
 *
 * @author: Jianjun
 * @date: 2020-10-20 16:03
 * @version:
 */
public class LazySingleton {

    private volatile static LazySingleton instance;

    /**
     * 防止被外部实例化
     */
    private LazySingleton() {
    }

    /**
     * 通过 volatile 保证线程安全
     * @return
     */
    public static LazySingleton getInstance(){
        return new LazySingleton();
    }

    /**
     * double check
     * @return
     */
    public static LazySingleton getInstance2(){
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    return new LazySingleton();
                }
            }
        }
        return instance;
    }
}
