package com.point.common.biz;

import java.io.Serializable;

/**
 *   
 * <p>通用返回结果</p>
 *
 * @author: huixing
 * @date: 2018/3/2 
 * @since V1.0
 *  
 */
public class BizResult<T> implements Serializable {
    private T data;

    private boolean flag;

    private String desc;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BizResult() {
    }

    public static <T> BizResult<T> create(T data) {

        BizResult<T> bizResult = new BizResult<T>();

        bizResult.setData(data);

        if (data == null) {
            bizResult.setDesc("操作失败");
            bizResult.setFlag(false);
        } else {
            bizResult.setFlag(true);
            bizResult.setDesc("操作成功");
        }
        return bizResult;
    }

}
