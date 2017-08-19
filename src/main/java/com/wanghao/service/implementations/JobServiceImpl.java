package com.wanghao.service.implementations;

import com.wanghao.db.dao.JobInfoDao;
import com.wanghao.service.interfaces.JobService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by wanghao on 8/18/17.
 */
@Service
public class JobServiceImpl implements JobService{
    private static final Logger logger = Logger.getLogger(JobServiceImpl.class);

    @Resource
    private JobInfoDao jobInfoDao;

    @Override
    public void updateJobInfo(long id, int runCount, int jobStatus, Date updateTime) {
        jobInfoDao.updateById(id, runCount, jobStatus, updateTime);
    }
}
