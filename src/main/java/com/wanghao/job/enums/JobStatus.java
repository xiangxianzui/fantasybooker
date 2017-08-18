package com.wanghao.job.enums;

/**
 * Created by wanghao on 8/18/17.
 */
public enum JobStatus {
    FAIL(0, "失败"),
    SUCCESS(1, "成功"),
    WAIT(2, "待处理");

    public final int VALUE;
    public final String EXTVALUE;

    private JobStatus(int value, String extValue) {
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
