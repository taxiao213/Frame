package com.hr.bean;

import com.hr.utils.net.Api.BaseResultEntity;

import java.util.List;

/**
 * Created by aaa on 2017/3/31.
 */

public class AdressListInfo extends BaseResultEntity {

    private List<AddressBook> data;

    public List<AddressBook> getList() {
        return data;
    }

    public void setList(List<AddressBook> list) {
        this.data = list;
    }
}
