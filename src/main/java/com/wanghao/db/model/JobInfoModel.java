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

    private String jobExtension;

    private String jobDescription;

    // 当前重试次数
    private int runCount;

    // 最大重试次数
    private int maxCount;

    // 任务状态
    private JobStatus jobStatus;

    // 任务类型
    private JobType jobType;

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

    public String getJobExtension() {
        return jobExtension;
    }

    public void setJobExtension(String jobExtension) {
        this.jobExtension = jobExtension;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
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