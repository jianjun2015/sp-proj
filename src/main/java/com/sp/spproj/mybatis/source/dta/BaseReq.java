package com.sp.spproj.mybatis.source.dta;

import lombok.Data;

@Data
public class BaseReq {
    //当前页
    private int pageNum = 1;
    private int pageSize = 20;
}
