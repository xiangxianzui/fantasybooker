package com.wanghao.util;

import com.wanghao.db.model.UserInfoModel;
import com.wanghao.service.enums.EmailType;
import com.wanghao.task.EmailTask;
import com.wanghao.task.EmailTaskObserver;
import com.wanghao.task.bean.EmailInfoBean;
import org.apache.log4j.Logger;

/**
 * Created by wanghao on 8/17/17.
 */
public class EmailUtil {
    private static final Logger logger = Logger.getLogger(EmailUtil.class);

    public static void sendEmail(EmailInfoBean emailInfoBean){
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
            if(emailType == EmailType.ACTIVATION_NOTIFY.value()){
                logger.info("开始向"+toEmail+"发送提醒激活邮件");
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
