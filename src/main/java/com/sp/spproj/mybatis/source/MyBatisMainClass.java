package com.sp.spproj.mybatis.source;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sp.spproj.mybatis.source.dta.CondPram;
import com.sp.spproj.mybatis.source.entity.TDemo;
import com.sp.spproj.mybatis.source.mapper.TDemoMapper;
import com.sp.spproj.mybatis.source.page.pagination.ZmPage;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 功能描述:
 *
 * @author: Jianjun
 * @date: 2020-10-09 15:04
 * @version:
 */
public class MyBatisMainClass {

    public static void main(String[] args) throws IOException {

        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //构建session工厂
        SqlSessionFactory sessionFactory =new  SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sessionFactory.openSession();

        TDemoMapper demoMapper = sqlSession.getMapper(TDemoMapper.class);
//        demoMapper.addRecord(new TDemo(3L, "111"));
//
//        demoMapper.getRecordById(3L);
//
//        demoMapper.addRecord(new TDemo(3L, "111"));
//
        Page page = PageHelper.startPage(1,20);
        List<TDemo> tDemos = demoMapper.selectByCond(CondPram.builder().id(1L).build());

        System.out.println(new ZmPage<>(page, tDemos));

    }

}
