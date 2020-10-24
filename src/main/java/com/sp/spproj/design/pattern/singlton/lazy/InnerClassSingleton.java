package com.sp.spproj.design.pattern.singlton.lazy;

/**
 * 功能描述: 静态内部类
 *
 * @author: Jianjun
 * @date: 2020-10-20 16:13
 * @version:
 */
public class InnerClassSingleton {

    private static class InnerClassHolder{
        private static InnerClassSingleton instance= new InnerClassSingleton();
    }

    private InnerClassSingleton() {
    }

    /**
     * 静态内部类 原理同 饿汉模式
     * @return
     */
    public static InnerClassSingleton getInstance(){
        return InnerClassHolder.instance;
    }
}
