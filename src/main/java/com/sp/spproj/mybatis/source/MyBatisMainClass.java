package com.sp.spproj.mybatis.source;

import com.alibaba.fastjson.JSON;
import com.sp.spproj.excel.ExcelUtil;
import com.sp.spproj.mybatis.source.entity.TDemo;
import com.sp.spproj.mybatis.source.mapper.TDemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: Jianjun
 * @date: 2020-10-09 15:04
 * @version:
 */
@Slf4j
public class MyBatisMainClass {

    public static void main(String[] args) throws IOException {

        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //构建session工厂
        SqlSessionFactory sessionFactory =new  SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sessionFactory.openSession(true);

        TDemoMapper demoMapper = sqlSession.getMapper(TDemoMapper.class);
        demoMapper.addRecord(new TDemo(5L, "666"));

        String sqlPlus = buildSqlPlus();
        List<Map> recordById = demoMapper.getRecordByIds(sqlPlus);
        System.out.println(JSON.toJSONString(recordById));

        String sheetName = "积分";
        String pattern = "yyyy-MM-dd HH:mm:ss";
        buildExcel(recordById, buildMap(), buildHeader(), sheetName, pattern);
    }

    private static void buildExcel(List<Map> data, LinkedHashMap<String, String> map, String[] header, String sheetName, String pattern) {

        long beginTime = System.currentTimeMillis();
        try {
            // 生成文件，写入内容，可百万级别数据,只要1分钟
            XSSFWorkbook excel = ExcelUtil.exportExcel(data, map, header, "积分", pattern);
            FileOutputStream fout = new FileOutputStream("./point.xlsx");
            excel.write(fout);
            fout.close();

            long endTime = System.currentTimeMillis();
            log.info("Cast time : " + (endTime - beginTime));
            log.info("excel生成成功");
        } catch (Exception e) {
            log.error("excel生成成功", e);
        }
    }

    private static String buildSqlPlus() {
        return "id,name,30 'age' from t_demo";
    }

    private static String[] buildHeader() {
        return new String[] { "id", "name", "age" };
    }

    private static LinkedHashMap<String, String> buildMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("id", "ID");
        map.put("name", "姓名");
        map.put("age", "年龄");
        return map;
    }

}
