package com.hr.bean;

import com.hr.utils.net.Api.BaseResultEntity;

/**
 * Created by aaa on 2017/3/29.
 */

public class LoginInfo extends BaseResultEntity {


    private String loginToken;
    private String userId;
    private String userAccount;


    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "resMsg='" + resMsg + '\'' +
                ", resCode=" + resCode +
                ", loginToken='" + loginToken + '\'' +
                ", userId='" + userId + '\'' +
                ", userAccount='" + userAccount + '\'' +
                '}';
    }
}
