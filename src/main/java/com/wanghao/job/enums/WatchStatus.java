package com.wanghao.job.enums;

/**
 * Created by wanghao on 8/19/17.
 */
public enum WatchStatus {
    WATCHING(0, "关注中"),
    NO_WATCHING(1, "未关注");

    public final int VALUE;
    public final String EXTVALUE;

    private WatchStatus(int value, String extValue) {
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
