package com.sp.spproj.mybatis.source.mapper;

import com.sp.spproj.mybatis.source.entity.TDemo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: Jianjun
 * @date: 2020-10-09 16:03
 * @version:
 */
@Mapper
public interface TDemoMapper {

//    @Insert("insert ignore into t_demo(id,name)values(#{id}, #{name})")
    Integer addRecord(TDemo tDemo);

//    @Select("select * from t_demo where id = #{id}")
    TDemo getRecordById(@Param("id") Long id);

    List<Map> getRecordByIds(@Param("sqlPlus") String sqlPlus);

}
