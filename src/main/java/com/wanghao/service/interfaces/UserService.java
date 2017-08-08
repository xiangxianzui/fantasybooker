package com.wanghao.service.interfaces;

import com.wanghao.db.model.UserInfoModel;

import javax.servlet.http.HttpSession;

/**
 * Created by wanghao on 8/5/17.
 */

public interface UserService {
    public String register(UserInfoModel userInfoModel);

    public String login(UserInfoModel curUser);

    public String logout(HttpSession session);

    public String activate(String nickName, String userCode);

    public String findPsw(UserInfoModel findPswUser);

    public String resetPsw(UserInfoModel resetPswUser);
}
