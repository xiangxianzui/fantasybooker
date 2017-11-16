package com.wanghao.job;

import com.wanghao.db.dao.JobInfoDao;
import com.wanghao.db.model.JobInfoModel;
import com.wanghao.job.enums.JobStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanghao on 8/15/17.
 * spring-tast实现定时任务，参考了http://gong1208.iteye.com/blog/1773177
 */
@Component("scanJobInfo")
public class ScanJobInfo {

    private static final Logger logger = Logger.getLogger(ScanJobInfo.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private ApplicationContext applicationContext;

    /* http://cron.qqe2.com/这个网站生成cron字符串 */
    /* 用途一：可以用来在每月最后一天执行定时任务，
    检查所有注册了超过3天但仍没有激活的用户，
    给这些用户再次发送激活邮件提醒激活 */
    /* 用途二：可以通过给用户界面右上角弹窗形式提示用户商品价格变动，
     * 或者让用户自己创建降价提醒等定时任务,定时任务触发条件？ */

/*    private static final long ONE_MINUTE = 60 * 1000;
    private static final long ONE_HOUR = 60 * ONE_MINUTE;
    private static final long ONE_DAY = 24 * ONE_HOUR;*/

    @Resource
    private JobInfoDao jobInfoDao;

    /* 每小时执行一次  0 0 0/1 * * ? */
     @Scheduled(fixedRate = 600000)
    public void scanJobInfo(){
         logger.info("执行定时任务:扫描job_info表");
         List<JobInfoModel> jobs = jobInfoDao.queryAll();
         if(CollectionUtils.isEmpty(jobs)){
             logger.info("job_info表为空，定时任务执行结束");
             return;
         }
         for (JobInfoModel job : jobs){
             int jobStatus = job.getJobStatus();
             int runCount = job.getRunCount();
             int maxCount = job.getMaxCount();
             if(jobStatus == JobStatus.FAIL.value() && runCount <= maxCount){
                 //将该job扔进线程池

                 JobExecutor jobExecutor = (JobExecutor)applicationContext.getBean("jobExecutor", job);
                 threadPoolTaskExecutor.execute(jobExecutor);
                 //new Thread(new JobExecutor(threadPoolTaskExecutor, job)).start();
             }
             if(jobStatus == JobStatus.WAIT.value() && runCount <= maxCount){
                 //将该job扔进线程池
                 JobExecutor jobExecutor = (JobExecutor)applicationContext.getBean("jobExecutor", job);
                 threadPoolTaskExecutor.execute(jobExecutor);
                 //new Thread(new JobExecutor(threadPoolTaskExecutor, job)).start();
             }
         }
         logger.info("执行定时任务结束");
         /*long currentTime = System.currentTimeMillis();
         Date nowTime = new Date(currentTime-3*ONE_DAY);
         List<UserInfoModel> users = userInfoDao.queryByRegisterTime(nowTime);
         for (UserInfoModel user : users){
             EmailInfoBean emailInfoBean = new EmailInfoBean();
             emailInfoBean.setRetry(0);
             emailInfoBean.setEmailType(EmailType.ACTIVATION_NOTIFY.value());
             emailInfoBean.setUserInfoModel(user);
             EmailUtil.sendEmail(emailInfoBean);
         }*/
    }
}
