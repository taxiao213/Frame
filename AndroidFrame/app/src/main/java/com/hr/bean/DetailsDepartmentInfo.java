package com.hr.bean;

/**
 * Created by Han on 2017/4/1.
 */

public class DetailsDepartmentInfo {
    private String scheduleWorkdept;
    private String doctor;
    private String week;
    private String midday;
    private String netLimit;
    private String startTime;
    private String endTime;
    private String appFlag;
    private String isStop;
    private String reggrade;
    private String id;
    private String stop_flg;
    private String del_flg;


    public void setScheduleWorkdept(String scheduleWorkdept) {
        this.scheduleWorkdept = scheduleWorkdept;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setMidday(String midday) {
        this.midday = midday;
    }

    public void setNetLimit(String netLimit) {
        this.netLimit = netLimit;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setAppFlag(String appFlag) {
        this.appFlag = appFlag;
    }

    public void setIsStop(String isStop) {
        this.isStop = isStop;
    }

    public void setReggrade(String reggrade) {
        this.reggrade = reggrade;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStop_flg(String stop_flg) {
        this.stop_flg = stop_flg;
    }

    public void setDel_flg(String del_flg) {
        this.del_flg = del_flg;
    }

    public String getScheduleWorkdept() {
        return scheduleWorkdept;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getWeek() {
        return week;
    }

    public String getMidday() {
        return midday;
    }

    public String getNetLimit() {
        return netLimit;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getAppFlag() {
        return appFlag;
    }

    public String getIsStop() {
        return isStop;
    }

    public String getReggrade() {
        return reggrade;
    }

    public String getId() {
        return id;
    }

    public String getStop_flg() {
        return stop_flg;
    }

    public String getDel_flg() {
        return del_flg;
    }

    @Override
    public String toString() {
        return "DetailsDepartmentInfo{" +
                "scheduleWorkdept='" + scheduleWorkdept + '\'' +
                ", doctor='" + doctor + '\'' +
                ", week='" + week + '\'' +
                ", midday='" + midday + '\'' +
                ", netLimit='" + netLimit + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", appFlag='" + appFlag + '\'' +
                ", isStop='" + isStop + '\'' +
                ", reggrade='" + reggrade + '\'' +
                ", id='" + id + '\'' +
                ", stop_flg='" + stop_flg + '\'' +
                ", del_flg='" + del_flg + '\'' +
                '}';
    }


}
