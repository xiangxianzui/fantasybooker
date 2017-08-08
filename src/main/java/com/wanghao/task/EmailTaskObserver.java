package com.wanghao.task;

import com.wanghao.task.bean.EmailInfoBean;
import org.apache.log4j.Logger;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by wanghao on 8/7/17.
 * 观察者 线程 类
 */
public class EmailTaskObserver implements Observer {
    private static final Logger logger = Logger.getLogger(EmailTaskObserver.class);

    private static final int MAX_RETRY = 3;//最多重发次数

    @Override
    public void update(Observable o, Object arg) {
        //重启线程
        logger.info("发送邮件失败，5秒后重新发送");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error(e.getStackTrace());
        }
        if(arg != null){
            int retry = ((EmailInfoBean)arg).getRetry();
            logger.info("当前重发邮件次数："+retry);
            if(retry <= MAX_RETRY){
                retry++;
                ((EmailInfoBean) arg).setRetry(retry);
                EmailTask emailTask = new EmailTask((EmailInfoBean)arg);
                EmailTaskObserver taskObserver = new EmailTaskObserver();
                //需要将观察者类加入到被观察者的观察者列表中
                emailTask.addObserver(taskObserver);
                new Thread(emailTask).start();
            }
            else{
                logger.info("重发次数超过限制："+MAX_RETRY+"次");
            }
        }
    }
}
