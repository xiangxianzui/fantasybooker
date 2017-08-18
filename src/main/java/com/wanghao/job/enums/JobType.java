package com.wanghao.job.enums;

/**
 * Created by wanghao on 8/18/17.
 */
public enum JobType {
    EMAIL(0, "邮件任务"),
    SITE_MAIL(1, "站内信任务");

    public final int VALUE;
    public final String EXTVALUE;

    private JobType(int value, String extValue) {
        VALUE = value;
        EXTVALUE = extValue;
    }

    public int value() {
        return this.VALUE;
    }

    public String extValue() {
        return this.EXTVALUE;
    }
}
