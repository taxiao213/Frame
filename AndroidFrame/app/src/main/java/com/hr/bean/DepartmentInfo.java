package com.hr.bean;

/**
 * 挂号科室查询
 * Created by Han on 2017/4/1.
 */

public class DepartmentInfo {


    /**
     * hospitalId : 3
     * deptId : 402880a15b4b40d4015b4b48c6f90001
     * deptName : 简易门诊
     * deptCode : 1001
     * deptBrev : 简易门诊
     * deptEname : JL
     * deptDistrict : 1
     * deptDistrictName : 河西院区
     * deptPinYin : JYMZ
     * deptWb : TJUY
     * deptHasson : 0
     * deptLevel : 1
     * deptOrder : 1
     * deptType : C
     * deptIsforregister : 1
     * deptIsforaccounting : 0
     * createUser : zpty1002
     * createDept : 8520
     * createTime : 2017-04-08 09:58:32
     * updateUser : honry
     * updateTime : 2017-04-10 15:42:51
     * stop_flg : 0
     * del_flg : 0
     */

    private int hospitalId;
    private String deptId;
    private String deptName;
    private String deptCode;
    private String deptBrev;
    private String deptEname;
    private int deptDistrict;
    private String deptDistrictName;
    private String deptPinYin;
    private String deptWb;
    private int deptHasson;
    private int deptLevel;
    private int deptOrder;
    private String deptType;
    private int deptIsforregister;
    private int deptIsforaccounting;
    private String createUser;
    private String createDept;
    private String createTime;
    private String updateUser;
    private String updateTime;
    private int stop_flg;
    private int del_flg;

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptBrev() {
        return deptBrev;
    }

    public void setDeptBrev(String deptBrev) {
        this.deptBrev = deptBrev;
    }

    public String getDeptEname() {
        return deptEname;
    }

    public void setDeptEname(String deptEname) {
        this.deptEname = deptEname;
    }

    public int getDeptDistrict() {
        return deptDistrict;
    }

    public void setDeptDistrict(int deptDistrict) {
        this.deptDistrict = deptDistrict;
    }

    public String getDeptDistrictName() {
        return deptDistrictName;
    }

    public void setDeptDistrictName(String deptDistrictName) {
        this.deptDistrictName = deptDistrictName;
    }

    public String getDeptPinYin() {
        return deptPinYin;
    }

    public void setDeptPinYin(String deptPinYin) {
        this.deptPinYin = deptPinYin;
    }

    public String getDeptWb() {
        return deptWb;
    }

    public void setDeptWb(String deptWb) {
        this.deptWb = deptWb;
    }

    public int getDeptHasson() {
        return deptHasson;
    }

    public void setDeptHasson(int deptHasson) {
        this.deptHasson = deptHasson;
    }

    public int getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(int deptLevel) {
        this.deptLevel = deptLevel;
    }

    public int getDeptOrder() {
        return deptOrder;
    }

    public void setDeptOrder(int deptOrder) {
        this.deptOrder = deptOrder;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public int getDeptIsforregister() {
        return deptIsforregister;
    }

    public void setDeptIsforregister(int deptIsforregister) {
        this.deptIsforregister = deptIsforregister;
    }

    public int getDeptIsforaccounting() {
        return deptIsforaccounting;
    }

    public void setDeptIsforaccounting(int deptIsforaccounting) {
        this.deptIsforaccounting = deptIsforaccounting;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getStop_flg() {
        return stop_flg;
    }

    public void setStop_flg(int stop_flg) {
        this.stop_flg = stop_flg;
    }

    public int getDel_flg() {
        return del_flg;
    }

    public void setDel_flg(int del_flg) {
        this.del_flg = del_flg;
    }

    @Override
    public String toString() {
        return "DepartmentInfo{" +
                "hospitalId=" + hospitalId +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", deptBrev='" + deptBrev + '\'' +
                ", deptEname='" + deptEname + '\'' +
                ", deptDistrict=" + deptDistrict +
                ", deptDistrictName='" + deptDistrictName + '\'' +
                ", deptPinYin='" + deptPinYin + '\'' +
                ", deptWb='" + deptWb + '\'' +
                ", deptHasson=" + deptHasson +
                ", deptLevel=" + deptLevel +
                ", deptOrder=" + deptOrder +
                ", deptType='" + deptType + '\'' +
                ", deptIsforregister=" + deptIsforregister +
                ", deptIsforaccounting=" + deptIsforaccounting +
                ", createUser='" + createUser + '\'' +
                ", createDept='" + createDept + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", stop_flg=" + stop_flg +
                ", del_flg=" + del_flg +
                '}';
    }
}
