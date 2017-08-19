package com.wanghao.db.dao;

import com.wanghao.db.model.JobInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wanghao on 8/18/17.
 */
public interface JobInfoDao {
    void save(JobInfoModel jobInfoModel);

    JobInfoModel findById(@Param("id") long id);

    List<JobInfoModel> queryAll();

    void updateById(@Param("id") long id, @Param("runCount") int runCount,
                    @Param("jobStatus") int jobStatus, @Param("updateTime") Date updateTime);
}
