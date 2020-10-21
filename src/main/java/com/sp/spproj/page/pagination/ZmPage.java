package com.sp.spproj.page.pagination;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.pagehelper.Page;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @param <T>
 * @date 2018-09-09 20：13
 * 分页
 */
@Data
public class ZmPage<T> {
    /**
     * 当前页
     */
    private int pageNum = 1;
    /**
     * 每页显示记录数
     */
    private int pageSize = 20;
    /**
     * 总页数
     */
    private int totalPage = 0;

    /**
     * 总记录数
     */
    private long total = 0;

    /**
     * 结果集
     */
    private List<T> data;

    @JSONField(serialize = false)
    private Page<T> page;


    public ZmPage() {
    }

    public ZmPage(Page page, List<T> list) {
        if (page != null && CollectionUtils.isNotEmpty(list)) {
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.data = list;
            this.total = page.getTotal();
            this.totalPage = page.getPages();
        }
    }
}
