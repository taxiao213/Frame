package com.hr.bean;

import com.hr.utils.net.Api.BaseResultEntity;

import java.util.List;

/**
 * Created by aaa on 2017/4/1.
 */

public class ListDataInfo<T> extends BaseResultEntity {

    private List<T> data;

    private long nowDate;

    public long getNowDate() {
        return nowDate;
    }

    public void setNowDate(long nowDate) {
        this.nowDate = nowDate;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
