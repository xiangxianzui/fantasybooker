package com.wanghao.task.bean;

import com.wanghao.db.model.UserInfoModel;

/**
 * Created by wanghao on 8/8/17.
 */
public class EmailInfoBean {
    private UserInfoModel userInfoModel;

    private int emailType;

    private int retry;//当前的重发邮件次数

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    public int getEmailType() {
        return emailType;
    }

    public void setEmailType(int emailType) {
        this.emailType = emailType;
    }
}
