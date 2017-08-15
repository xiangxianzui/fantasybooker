package com.wanghao.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wanghao on 8/15/17.
 * spring-tast实现定时任务，参考了http://gong1208.iteye.com/blog/1773177
 */
@Component("myScheduledJob")
public class MyScheduledJob{

    /* http://cron.qqe2.com/这个网站生成cron字符串 */
    /* 可以用来在每月最后一天执行定时任务，
    检查所用注册了超过3天但仍没有激活的用户，
    给这些用户发送再次发送激活邮件提醒激活 */
    @Scheduled(cron = "0 0 0 L * ? *")
    public void job1(){
        System.out.println("执行定时任务1");
        //TODO
    }
}
