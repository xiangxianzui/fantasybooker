package com.wanghao.job;

import com.wanghao.db.model.JobInfoModel;
import com.wanghao.job.enums.JobResult;
import com.wanghao.service.interfaces.JobService;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;

/**
 * Created by wanghao on 8/18/17.
 */
public class ThreadPoolJob implements Callable<String>{
    private static final Logger logger = Logger.getLogger(ThreadPoolJob.class);

    // 执行任务所需要的数据
    private JobInfoModel threadPoolJobData;

    public ThreadPoolJob(JobInfoModel job) {
        this.threadPoolJobData = job;
    }

    @Override
    public synchronized String call() throws Exception {
        logger.info("【线程池】开始执行任务，任务id="+threadPoolJobData.getId());
        String result = "";
        // 便于观察，等待一段时间
        try {
            // long r = 5/0;
            for (int i = 0; i < 100000000; i++) {

            }
            result = JobResult.SUCCESS.extValue();
        } catch (Exception e) {
            e.printStackTrace();
            result = JobResult.FAIL.extValue();
        }
        threadPoolJobData = null;
        return result;
    }

    public static void doJob(JobInfoModel job){
        int jobType = job.getJobType();

    }

}
