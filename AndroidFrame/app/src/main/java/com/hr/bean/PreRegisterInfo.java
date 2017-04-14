package com.hr.bean;

/**
 * Created by aaa on 2017/4/11.
 */

public class PreRegisterInfo  {

    /**
     "preregisterDeptName":"鼻科一",
     "preregisterExpertName":"赵玉林",
     "preregisterGradeName":"主治医师",
     "id":"402880a15b577c0b015b5783bfb60003",
     "createUser":"zpty1001",
     "createTime":"2017-04-10 18:58:23",
     "stop_flg":0,
     "del_flg":0
     */
    private String preregisterNo; //编号
    private int preregisterIsnet;
    private String preregisterIsphone;
    private int preregisterDept;
    private String preregisterExpert;
    private String preregisterDate;
    private String preregisterCertificatesno;
    private String preregisterName;
    private String preregisterPhone;
    private int midday;
    private int seeFlag;
    private int appFlag;
    private int isFirst;
    private int status;
    private int missNumber;
    private String preregisterDeptName;
    private String preregisterExpertName;
    private String preregisterGradeName;
    private String id;
    private String createUser;
    private String createTime;
    private int stop_flg;
    private int del_flg;

    @Override
    public String toString() {
        return "PreRegisterInfo{" +
                "preregisterNo='" + preregisterNo + '\'' +
                ", preregisterIsnet=" + preregisterIsnet +
                ", preregisterIsphone='" + preregisterIsphone + '\'' +
                ", preregisterDept='" + preregisterDept + '\'' +
                ", preregisterExpert='" + preregisterExpert + '\'' +
                ", preregisterDate='" + preregisterDate + '\'' +
                ", preregisterCertificatesno='" + preregisterCertificatesno + '\'' +
                ", preregisterName='" + preregisterName + '\'' +
                ", preregisterPhone='" + preregisterPhone + '\'' +
                ", midday=" + midday +
                ", seeFlag=" + seeFlag +
                ", appFlag=" + appFlag +
                ", isFirst=" + isFirst +
                ", status=" + status +
                ", missNumber=" + missNumber +
                ", preregisterDeptName='" + preregisterDeptName + '\'' +
                ", preregisterExpertName='" + preregisterExpertName + '\'' +
                ", preregisterGradeName='" + preregisterGradeName + '\'' +
                ", id='" + id + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime='" + createTime + '\'' +
                ", stop_flg=" + stop_flg +
                ", del_flg=" + del_flg +
                '}';
    }

    public String getPreregisterNo() {
        return preregisterNo;
    }

    public void setPreregisterNo(String preregisterNo) {
        this.preregisterNo = preregisterNo;
    }

    public int getPreregisterIsnet() {
        return preregisterIsnet;
    }

    public void setPreregisterIsnet(int preregisterIsnet) {
        this.preregisterIsnet = preregisterIsnet;
    }

    public String getPreregisterIsphone() {
        return preregisterIsphone;
    }

    public void setPreregisterIsphone(String preregisterIsphone) {
        this.preregisterIsphone = preregisterIsphone;
    }

    public int getPreregisterDept() {
        return preregisterDept;
    }

    public void setPreregisterDept(int preregisterDept) {
        this.preregisterDept = preregisterDept;
    }

    public String getPreregisterExpert() {
        return preregisterExpert;
    }

    public void setPreregisterExpert(String preregisterExpert) {
        this.preregisterExpert = preregisterExpert;
    }

    public String getPreregisterDate() {
        return preregisterDate;
    }

    public void setPreregisterDate(String preregisterDate) {
        this.preregisterDate = preregisterDate;
    }

    public String getPreregisterCertificatesno() {
        return preregisterCertificatesno;
    }

    public void setPreregisterCertificatesno(String preregisterCertificatesno) {
        this.preregisterCertificatesno = preregisterCertificatesno;
    }

    public String getPreregisterName() {
        return preregisterName;
    }

    public void setPreregisterName(String preregisterName) {
        this.preregisterName = preregisterName;
    }

    public String getPreregisterPhone() {
        return preregisterPhone;
    }

    public void setPreregisterPhone(String preregisterPhone) {
        this.preregisterPhone = preregisterPhone;
    }

    public int getMidday() {
        return midday;
    }

    public void setMidday(int midday) {
        this.midday = midday;
    }

    public int getSeeFlag() {
        return seeFlag;
    }

    public void setSeeFlag(int seeFlag) {
        this.seeFlag = seeFlag;
    }

    public int getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(int appFlag) {
        this.appFlag = appFlag;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMissNumber() {
        return missNumber;
    }

    public void setMissNumber(int missNumber) {
        this.missNumber = missNumber;
    }

    public String getPreregisterDeptName() {
        return preregisterDeptName;
    }

    public void setPreregisterDeptName(String preregisterDeptName) {
        this.preregisterDeptName = preregisterDeptName;
    }

    public String getPreregisterExpertName() {
        return preregisterExpertName;
    }

    public void setPreregisterExpertName(String preregisterExpertName) {
        this.preregisterExpertName = preregisterExpertName;
    }

    public String getPreregisterGradeName() {
        return preregisterGradeName;
    }

    public void setPreregisterGradeName(String preregisterGradeName) {
        this.preregisterGradeName = preregisterGradeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
}
