package com.wanghao.service.implementations;

import com.wanghao.common.Constant;
import com.wanghao.db.dao.UserInfoDao;
import com.wanghao.db.model.UserInfoModel;
import com.wanghao.service.enums.ActivateMsg;
import com.wanghao.service.enums.ActivateStatus;
import com.wanghao.service.enums.EmailType;
import com.wanghao.service.enums.FindPswMsg;
import com.wanghao.service.enums.LoginMsg;
import com.wanghao.service.enums.LogoutMsg;
import com.wanghao.service.enums.RegisterMsg;
import com.wanghao.service.interfaces.UserService;
import com.wanghao.task.EmailTask;
import com.wanghao.task.EmailTaskObserver;
import com.wanghao.task.bean.EmailInfoBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by wanghao on 8/5/17.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private UserInfoDao userInfoDao;

    @Autowired
    private HttpServletRequest req;

    @Override
    public String register(UserInfoModel newUser) {
        String result = "";
        List<UserInfoModel> users = userInfoDao.queryAll();
        if (users == null){
            result = RegisterMsg.FAIL_DB.extValue();
            logger.error(result+"["+Thread.currentThread().getName()+"]");
            return result;
        }
        else {
            boolean isExist = false;
            for (UserInfoModel user : users){
                if(user.getNickname().equals(newUser.getNickname())){
                    isExist = true;
                    result = RegisterMsg.FAIL_NICK.extValue();
                    break;
                }
                if(user.getEmail().equals(newUser.getEmail())){
                    isExist = true;
                    result = RegisterMsg.FAIL_EMAIL.extValue();
                    break;
                }
            }
            if(isExist){
                logger.info(result+"["+Thread.currentThread().getName()+"]");
                return result;
            }
            else {
                InitUserInfoModel(newUser);
                //生成唯一识别码,例如：s = 9e15a007-074d-430b-8511-5fc805a61e5a
                String s = UUID.randomUUID().toString();
                String userCode =  s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
                newUser.setRegisterTime(new Date());
                newUser.setUserCode(userCode);
                userInfoDao.save(newUser);
                result = RegisterMsg.SUCCESS.extValue();
                logger.info(result+"["+Thread.currentThread().getName()+"]");
                //向用户发送注册成功邮件
                EmailInfoBean emailInfoBean = new EmailInfoBean();
                emailInfoBean.setEmailType(EmailType.REGISTER_SUCCESS.value());
                emailInfoBean.setUserInfoModel(newUser);
                sendEmail(emailInfoBean);
                return result;
            }
        }
    }

    @Override
    public String login(UserInfoModel loginUser) {
        String result;
        InitUserInfoModel(loginUser);
        String nickname = loginUser.getNickname();
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();
        UserInfoModel userInfoModel1 = userInfoDao.findByNickname(nickname);
        UserInfoModel userInfoModel2 = userInfoDao.findByEmail(email);
        if(userInfoModel1 == null && userInfoModel2 == null){
            result = LoginMsg.FAIL_NO_USER.extValue();
            logger.info(result + "[" + Thread.currentThread().getName() + "]");
            return result;
        }
        if(userInfoModel1 != null){
            if(userInfoModel1.getActivated() == ActivateStatus.ACTIVATE_YES.value()){
                if(userInfoModel1.getPassword().equals(password)){
                    req.getSession().setAttribute(Constant.LOGIN_USER, userInfoModel1);
                    result = LoginMsg.SUCCESS_NICKNAME.extValue();
                    logger.info(result+"["+Thread.currentThread().getName()+"]");
                    return result;
                }
                else {
                    result = LoginMsg.FAIL_PASSWORD.extValue();
                    logger.info(result+"["+Thread.currentThread().getName()+"]");
                    return result;
                }
            }
            else {
                result = LoginMsg.FAIL_NOT_ACTIVATE.extValue();
                logger.info(result+"["+Thread.currentThread().getName()+"]");
                return result;
            }
        }
        if(userInfoModel2 != null){
            if(userInfoModel2.getActivated() == ActivateStatus.ACTIVATE_YES.value()){
                if(userInfoModel2.getPassword().equals(password)){
                    req.getSession().setAttribute(Constant.LOGIN_USER, userInfoModel2);
                    result = LoginMsg.SUCCESS_EMAIL.extValue();
                    logger.info(result+"["+Thread.currentThread().getName()+"]");
                    return result;
                }
                else {
                    result = LoginMsg.FAIL_PASSWORD.extValue();
                    logger.info(result+"["+Thread.currentThread().getName()+"]");
                    return result;
                }
            }
            else {
                result = LoginMsg.FAIL_NOT_ACTIVATE.extValue();
                logger.info(result+"["+Thread.currentThread().getName()+"]");
                return result;
            }
        }
        result = LoginMsg.FAIL_DB.extValue();
        logger.info(result+"["+Thread.currentThread().getName()+"]");
        return result;
    }

    @Override
    public String logout(HttpSession session) {
        String result;
        UserInfoModel curUser = (UserInfoModel)session.getAttribute(Constant.LOGIN_USER);
        if(curUser == null){
            result = LogoutMsg.FAIL.extValue();
            logger.info(result+"["+Thread.currentThread().getName()+"]");
        }
        else {
            session.invalidate();
            result = LogoutMsg.SUCCESS.extValue();
            logger.info(result+"["+Thread.currentThread().getName()+"]");
        }
        return result;
    }

    @Override
    public String activate(String nickName, String userCode) {
        String result = "";
        if(nickName != null && userCode != null){
            UserInfoModel user = userInfoDao.findByNickname(nickName);
            if(user == null){
                result = ActivateMsg.FAIL_PARAMS.extValue();
                logger.info(result+"["+Thread.currentThread().getName()+"]");
            }
            else {
                if(user.getActivated()== ActivateStatus.ACTIVATE_YES.value()){
                    result = ActivateMsg.FAIL_REPEAT.extValue();
                    logger.info(result+"["+Thread.currentThread().getName()+"]");
                }
                if(user.getActivated()== ActivateStatus.ACTIVATE_NO.value()){
                    if(user.getUserCode().equals(userCode)){
                        userInfoDao.updateActivatedByNickname(nickName, ActivateStatus.ACTIVATE_YES.value());
                        result = ActivateMsg.SUCCESS.extValue();
                        logger.info(result+"["+Thread.currentThread().getName()+"]");
                    }
                    else{
                        result = ActivateMsg.FAIL_NOT_MATCH.extValue();
                        logger.info(result+"["+Thread.currentThread().getName()+"]");
                    }
                }
            }
        }
        else{
            result = ActivateMsg.FAIL_PARAMS.extValue();
            logger.info(result+"["+Thread.currentThread().getName()+"]");
        }
        return result;
    }

    @Override
    public String findPsw(UserInfoModel findPswUser) {
        String result;
        InitUserInfoModel(findPswUser);
        String email = findPswUser.getEmail();
        logger.info(email);
        UserInfoModel userInfoModel = userInfoDao.findByEmail(email);
        if(userInfoModel == null){
            result = FindPswMsg.FAIL_NO_USER.extValue();
            logger.info(result+"["+Thread.currentThread().getName()+"]");
        }
        else {
            result = FindPswMsg.SUCCESS_EMAIL.extValue();
            logger.info(result+"["+Thread.currentThread().getName()+"]");
            EmailInfoBean emailInfoBean = new EmailInfoBean();
            emailInfoBean.setUserInfoModel(userInfoModel);
            emailInfoBean.setEmailType(EmailType.FIND_PASSWORD.value());
            sendEmail(emailInfoBean);
        }
        return result;
    }

    @Override
    public String resetPsw(UserInfoModel resetPswUser) {
        String result;
        String nickName = resetPswUser.getNickname();
        String userCode = resetPswUser.getUserCode();
        String newPassword = resetPswUser.getPassword();
        if(nickName != null && userCode != null && newPassword != null){
            UserInfoModel user = userInfoDao.findByNickname(nickName);
            if(user == null){
                result = FindPswMsg.FAIL_PARAMS.extValue();
                logger.info(result+"["+Thread.currentThread().getName()+"]");
            }
            else {
                if(user.getUserCode().equals(userCode)){
                    userInfoDao.updatePasswordByNickname(nickName, newPassword);
                    result = FindPswMsg.SUCCESS.extValue();
                    logger.info(result+"["+Thread.currentThread().getName()+"]");
                }
                else{
                    result = FindPswMsg.FAIL_NOT_MATCH.extValue();
                    logger.info(result+"["+Thread.currentThread().getName()+"]");
                }
            }
        }
        else{
            result = FindPswMsg.FAIL_PARAMS.extValue();
            logger.info(result+"["+Thread.currentThread().getName()+"]");
        }
        return result;
    }

    public void InitUserInfoModel(UserInfoModel userInfoModel){
        try {
            if (userInfoModel != null){
                if(userInfoModel.getNickname() == null){
                    userInfoModel.setNickname("");
                }
                if(userInfoModel.getEmail() == null){
                    userInfoModel.setEmail("");
                }
                if(userInfoModel.getPhone() == null){
                    userInfoModel.setPhone("");
                }
                if(userInfoModel.getAddress() == null){
                    userInfoModel.setAddress("");
                }
                if(userInfoModel.getPassword() == null){
                    userInfoModel.setPassword("");
                }
                if(userInfoModel.getUserCode() == null){
                    userInfoModel.setUserCode("");
                }
                if(userInfoModel.getRegisterTime() == null){
                    userInfoModel.setRegisterTime(dateFormat.parse("2017-01-01 00:00:00"));
                }
            }
        } catch (ParseException e){
            logger.error(e.getStackTrace());
        }
    }

    public void sendEmail(EmailInfoBean emailInfoBean){
        UserInfoModel user = emailInfoBean.getUserInfoModel();
        int emailType = emailInfoBean.getEmailType();
        if(user != null && !user.getEmail().equals("")){
            String toEmail = user.getEmail();
            if(emailType == EmailType.REGISTER_SUCCESS.value()){
                logger.info("开始向"+toEmail+"发送注册成功邮件");
            }
            if(emailType == EmailType.FIND_PASSWORD.value()){
                logger.info("开始向"+toEmail+"发送找回密码邮件");
            }
            EmailTask emailTask = new EmailTask(emailInfoBean);
            EmailTaskObserver emailTaskObserver = new EmailTaskObserver();//观察者
            emailTask.addObserver(emailTaskObserver);//被观察者将观察者加入观察者队列
            new Thread(emailTask).start();
        }
        else{
            logger.info("收件人为空，不发送邮件");
        }
    }
}
