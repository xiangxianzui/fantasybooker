package com.wanghao.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wanghao.db.dao.BookInfoDao;
import com.wanghao.db.dao.JobInfoDao;
import com.wanghao.db.dao.UserBookWatchDao;
import com.wanghao.db.model.BookInfoModel;
import com.wanghao.db.model.JobInfoModel;
import com.wanghao.db.model.UserBookWatchModel;
import com.wanghao.job.enums.JobStatus;
import com.wanghao.job.enums.JobType;
import com.wanghao.job.enums.WatchStatus;
import com.wanghao.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wanghao on 8/19/17.
 */
@Component("scanBookUserWatch")
public class ScanBookUserWatch {
    private static final Logger logger = Logger.getLogger(ScanBookUserWatch.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        return threadPoolTaskExecutor;
    }

    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @Resource
    private UserBookWatchDao userBookWatchDao;

    @Resource
    private BookInfoDao bookInfoDao;

    @Resource
    private JobInfoDao jobInfoDao;

    /* 每小时执行一次  0 0 0/1 * * ? */
    @Scheduled(cron = "0 0/1 0/1 * * ?")
    public void scanUserBookWatch(){
        logger.info("执行定时任务:扫描表user_book_watch");
        List<UserBookWatchModel> watches = userBookWatchDao.queryAll();
        if(CollectionUtils.isEmpty(watches)){
            logger.info("user_book_watch表为空，定时任务执行结束");
            return;
        }
        for (UserBookWatchModel watch : watches){
            if(watch.getWatchStatus() == WatchStatus.WATCHING.value()){
                long bookId = watch.getBookId();
                BookInfoModel book = bookInfoDao.findById(bookId);
                double watchPrice = watch.getPrice().doubleValue() * watch.getDiscount().doubleValue();
                double nowPrice = book.getPrice().doubleValue()*book.getDiscount().doubleValue();
                //这本书是否比关注时降价了
                boolean isPrice = watchPrice-nowPrice > 0;
                //这本书是否只剩不到10本了
                boolean isAmount = book.getAmount() <= 10;
                if(isPrice || isAmount){
                    JobInfoModel newJob = new JobInfoModel();
                    newJob.setUserId(watch.getUserId());
                    newJob.setJobExt(buildJobExt(watch, book));
                    newJob.setJobDesc("描述");
                    newJob.setRunCount(0);
                    newJob.setMaxCount(Constant.JOB_MAX_COUNT);
                    newJob.setJobStatus(JobStatus.WAIT.value());
                    newJob.setJobType(JobType.EMAIL.value());
                    Date now = new Date();
                    newJob.setCreateTime(now);
                    newJob.setUpdateTime(now);
                    jobInfoDao.save(newJob);
                }
            }
            else{//不再被关注，删除该条数据
                userBookWatchDao.deleteById(watch.getId());
            }
        }
        logger.info("执行定时任务结束");
    }

    public static String buildJobExt(UserBookWatchModel watch, BookInfoModel book){
        double watchPrice = watch.getPrice().doubleValue() * watch.getDiscount().doubleValue();
        int watchAmount = watch.getAmount();
        double nowPrice = book.getPrice().doubleValue()*book.getDiscount().doubleValue();
        int nowAmount = book.getAmount();
        //这本书是否比关注时降价了
        boolean isPrice = (watchPrice-nowPrice) > 0;
        //这本书是否只剩不到10本了
        boolean isAmount = nowAmount <= 10;
        JSONObject jobExtNode = new JSONObject();
        JSONObject priceNode = new JSONObject();
        priceNode.put("watch", watchPrice);
        priceNode.put("now", nowPrice);
        priceNode.put("isPrice", isPrice);
        JSONObject amountNode = new JSONObject();
        amountNode.put("watch", watchAmount);
        amountNode.put("now", nowAmount);
        amountNode.put("isAmount", isAmount);
        jobExtNode.put("price", priceNode);
        jobExtNode.put("amount", amountNode);
        return JSONObject.toJSONString(jobExtNode);
    }
}
