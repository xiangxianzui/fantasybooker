package com.wanghao.task.bean;

import com.wanghao.db.model.UserInfoModel;

/**
 * Created by wanghao on 8/8/17.
 */
public class EmailInfoBean {
    private UserInfoModel userInfoModel;

    private int emailType;

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
