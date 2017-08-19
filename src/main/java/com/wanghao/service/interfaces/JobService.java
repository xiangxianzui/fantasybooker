package com.wanghao.service.interfaces;

import java.util.Date;

/**
 * Created by wanghao on 8/18/17.
 */
public interface JobService {
    void updateJobInfo(long id, int runCount, int jobStatus, Date updateTime);
}
