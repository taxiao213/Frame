package com.hr.bean;

/**
 * Created by aaa on 2017/4/1.
 */

public class DocInfo {
    private int scheduleWorkdept;//科室号码
    private String scheduleDeptname;// 科室名称
    private String scheduleDoctorname;// 医生名称
    private String doctor;// 医生编号
    private int week;
    private int midday;
    private int netLimit;
    private String startTime;
    private String endTime;
    private int appFlag;
    private int isStop;
    private String reggrade;

    private String id;
    private int stop_flg;
    private int del_flg;
    private boolean timeIsOut;

    private boolean numIsOut;

    public String getScheduleDeptname() {
        return scheduleDeptname;
    }

    public void setScheduleDeptname(String scheduleDeptname) {
        this.scheduleDeptname = scheduleDeptname;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctor() {
        return doctor;
    }

    public boolean isTimeIsOut() {
        return timeIsOut;
    }

    public void setTimeIsOut(boolean timeIsOut) {
        this.timeIsOut = timeIsOut;
    }

    public boolean isNumIsOut() {
        return numIsOut;
    }

    public void setNumIsOut(boolean numIsOut) {
        this.numIsOut = numIsOut;
    }

    public int getScheduleWorkdept() {
        return scheduleWorkdept;
    }

    public void setScheduleWorkdept(int scheduleWorkdept) {
        this.scheduleWorkdept = scheduleWorkdept;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMidday() {
        return midday;
    }

    public void setMidday(int midday) {
        this.midday = midday;
    }

    public int getNetLimit() {
        return netLimit;
    }

    public void setNetLimit(int netLimit) {
        this.netLimit = netLimit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(int appFlag) {
        this.appFlag = appFlag;
    }

    public int getIsStop() {
        return isStop;
    }

    public void setIsStop(int isStop) {
        this.isStop = isStop;
    }

    public String getReggrade() {
        return reggrade;
    }

    public void setReggrade(String reggrade) {
        this.reggrade = reggrade;
    }

    public String getScheduleDoctorname() {
        return scheduleDoctorname;
    }

    public void setScheduleDoctorname(String scheduleDoctorname) {
        this.scheduleDoctorname = scheduleDoctorname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "DocInfo{" +
                "scheduleWorkdept=" + scheduleWorkdept +
                ", scheduleDeptname='" + scheduleDeptname + '\'' +
                ", scheduleDoctorname='" + scheduleDoctorname + '\'' +
                ", doctor=" + doctor +
                ", week=" + week +
                ", midday=" + midday +
                ", netLimit=" + netLimit +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", appFlag=" + appFlag +
                ", isStop=" + isStop +
                ", reggrade='" + reggrade + '\'' +
                ", id='" + id + '\'' +
                ", stop_flg=" + stop_flg +
                ", del_flg=" + del_flg +
                ", timeIsOut=" + timeIsOut +
                ", numIsOut=" + numIsOut +
                '}';
    }
}
