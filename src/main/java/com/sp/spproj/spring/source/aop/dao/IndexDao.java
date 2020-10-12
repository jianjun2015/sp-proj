package com.sp.spproj.spring.source.aop.dao;

import com.sp.spproj.spring.source.aop.annotation.IndexAnno;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: wangjianjun
 * @date:2020-10-11 18:06
 */
@Component
public class IndexDao implements Dao{

    public void query(String xx){
        System.out.println("query,"+xx);
//        int i = 1 / 0;
    }

    @IndexAnno(value = true)
    public void query2(){
        System.out.println("query2");
    }
}
