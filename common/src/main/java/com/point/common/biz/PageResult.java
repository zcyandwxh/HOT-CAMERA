package com.point.common.biz;

import lombok.Data;

import java.io.Serializable;

/**
 *   
 * <p>分页包装类</p>
 *
 * @author: huixing
 * @date: 2018/3/10 
 * @since V1.0
 *  
 */
@Data
public class PageResult<X> implements Serializable {
    private static final long serialVersionUID = -1562872781998301951L;

    /**
     * 每一页的数量
     */
    private int pageSize;

    /**
     * 数据总数
     */
    private int total;

    /**
     * 页码
     */
    private int pageNo;

    /**
     * 总页数
     */
    private int pageNum;

    private X result;

    public PageResult(){

    }

    public PageResult(int pageSize, int pageNo, int total) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.total = total;
    }

    public PageResult(int pageSize, int total, int pageNo, X result) {
        this.pageSize = pageSize;
        this.total = total;
        this.pageNo = pageNo;
        this.result = result;
    }
}
