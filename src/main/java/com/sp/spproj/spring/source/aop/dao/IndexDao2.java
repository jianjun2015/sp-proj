package com.sp.spproj.spring.source.aop.dao;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: wangjianjun
 * @date:2020-10-11 18:06
 */
@Component
public class IndexDao2 implements Dao{

    public void query(String xx){
        System.out.println("query,"+xx);
    }
}
