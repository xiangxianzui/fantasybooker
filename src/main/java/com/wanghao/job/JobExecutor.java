package com.wanghao.job;

import com.wanghao.db.dao.JobInfoDao;
import com.wanghao.db.model.JobInfoModel;
import com.wanghao.job.enums.JobResult;
import com.wanghao.job.enums.JobStatus;
import com.wanghao.service.interfaces.JobService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanghao on 8/18/17.
 */
public class JobExecutor implements Runnable{
    private static final Logger logger = Logger.getLogger(JobExecutor.class);

    @Resource
    private JobService jobService;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private JobInfoModel job;

    public JobExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor, JobInfoModel job) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.job = job;
    }

    @Override
    public synchronized void run() {
        logger.info("将任务提交到线程池中");
        FutureTask<String> futureTask = new FutureTask<String>(new ThreadPoolJob(job));
        threadPoolTaskExecutor.execute(futureTask);
        // 在这里可以做别的任何事情
        String result = null;
        try {
            // 取得结果，同时设置超时执行时间为1秒。同样可以用future.get()，不设置执行超时时间取得结果
            result = futureTask.get();
        } catch (InterruptedException e) {
            futureTask.cancel(true);
        } catch (ExecutionException e) {
            futureTask.cancel(true);
        } catch (Exception e) {
            futureTask.cancel(true);
            // 超时后，进行相应处理
        } finally {
            logger.info("任务执行结果：" + result);
            if(StringUtils.equals(result, JobResult.FAIL.extValue())){
                int runCount = job.getRunCount() + 1;
                int jobStatus = JobStatus.FAIL.value();
                Date updateTime = new Date();
                jobService.updateJobInfo(job.getId(), runCount, jobStatus, updateTime);
            }
            if(StringUtils.equals(result, JobResult.SUCCESS.extValue())){
                int runCount = job.getRunCount() + 1;
                int jobStatus = JobStatus.SUCCESS.value();
                Date updateTime = new Date();
                jobService.updateJobInfo(job.getId(), runCount, jobStatus, updateTime);
            }
        }
    }
}
