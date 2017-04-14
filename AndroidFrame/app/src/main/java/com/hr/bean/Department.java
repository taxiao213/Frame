package com.hr.bean;

import com.hr.utils.net.Api.BaseResultEntity;

import java.util.ArrayList;

/**
 *
 * Created by Han on 2017/4/1.
 */

public class Department extends BaseResultEntity {

    private ArrayList<DepartmentInfo> data;


    public void setData(ArrayList<DepartmentInfo> data) {
        this.data = data;
    }

    public ArrayList<DepartmentInfo> getData() {

        return data;
    }

    @Override
    public String toString() {
        return "Department{" +
                "data=" + data +
                '}';
    }
}
