package com.wanghao.job;

import com.wanghao.db.dao.UserInfoDao;
import com.wanghao.db.model.UserInfoModel;
import com.wanghao.service.enums.EmailType;
import com.wanghao.task.bean.EmailInfoBean;
import com.wanghao.util.EmailUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wanghao on 8/15/17.
 * spring-tast实现定时任务，参考了http://gong1208.iteye.com/blog/1773177
 */
@Component("myScheduledJob")
public class MyScheduledJob{

    /* http://cron.qqe2.com/这个网站生成cron字符串 */
    /* 用途一：可以用来在每月最后一天执行定时任务，
    检查所有注册了超过3天但仍没有激活的用户，
    给这些用户再次发送激活邮件提醒激活 */
    /* 用途二：可以通过给用户界面右上角弹窗形式提示用户商品价格变动，
     * 或者让用户自己创建降价提醒等定时任务,定时任务触发条件？ */

    private static final long ONE_MINUTE = 60 * 1000;
    private static final long ONE_HOUR = 60 * ONE_MINUTE;
    private static final long ONE_DAY = 24 * ONE_HOUR;

    @Resource
    private UserInfoDao userInfoDao;

     @Scheduled(cron = "0 42 * * * ?")
    public void job1(){
         System.out.println("执行定时任务1");
         long currentTime = System.currentTimeMillis();
         Date nowTime = new Date(currentTime-3*ONE_DAY);
         List<UserInfoModel> users = userInfoDao.queryByRegisterTime(nowTime);
         for (UserInfoModel user : users){
             EmailInfoBean emailInfoBean = new EmailInfoBean();
             emailInfoBean.setRetry(0);
             emailInfoBean.setEmailType(EmailType.ACTIVATION_NOTIFY.value());
             emailInfoBean.setUserInfoModel(user);
             EmailUtil.sendEmail(emailInfoBean);
         }
    }
}
