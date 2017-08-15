package com.wanghao.service.interfaces;

import com.wanghao.db.model.UserInfoModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wanghao on 8/5/17.
 */

public interface UserService {
    String register(UserInfoModel userInfoModel);

    String login(UserInfoModel loginUser, HttpServletRequest req);

    String logout(HttpSession session);

    String activate(String nickName, String userCode);

    String findPsw(UserInfoModel findPswUser);

    String resetPsw(UserInfoModel resetPswUser);

    UserInfoModel viewUserDetail(String userCode);

    String updateUserDetail(UserInfoModel userInfoModel);
}
