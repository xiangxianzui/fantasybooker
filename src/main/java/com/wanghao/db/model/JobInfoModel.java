package com.wanghao.db.model;

import com.wanghao.job.enums.JobStatus;
import com.wanghao.job.enums.JobType;

import java.util.Date;

/**
 * Created by wanghao on 8/18/17.
 */
public class JobInfoModel {
    private long id;

    private long userId;

    private String jobExt;

    private String jobDesc;

    // 当前重试次数
    private int runCount;

    // 最大重试次数
    private int maxCount;

    // 任务状态
    private int jobStatus;

    // 任务类型
    private int jobType;

    // 创建时间
    private Date createTime;

    // 最后更新时间
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getJobExt() {
        return jobExt;
    }

    public void setJobExt(String jobExt) {
        this.jobExt = jobExt;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public int getRunCount() {
        return runCount;
    }

    public void setRunCount(int runCount) {
        this.runCount = runCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}