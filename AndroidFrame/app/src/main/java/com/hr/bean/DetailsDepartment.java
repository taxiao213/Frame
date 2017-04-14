package com.hr.bean;

import com.hr.utils.net.Api.BaseResultEntity;

import java.util.ArrayList;

/**
 * Created by Han on 2017/4/1.
 */

public class DetailsDepartment extends BaseResultEntity {

    private ArrayList<DetailsDepartmentInfo> data;

    public ArrayList<DetailsDepartmentInfo> getData() {
        return data;
    }

    public void setData(ArrayList<DetailsDepartmentInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DetailsDepartment{" +
                "data=" + data +
                '}';
    }
}
