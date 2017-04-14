package com.hr.utils.net.Api;

/**
 * 回调信息统一封装类
 * Created by WZG on 2016/7/16.
 */
public  class BaseResultEntity {
    //  判断标示
    protected int resCode;
    //    提示信息
    protected String resMsg;
    //显示数据（用户需要关心的数据）


    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
