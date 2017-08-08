package com.wanghao.service.enums;

/**
 * Created by wanghao on 8/7/17.
 * 激活状态
 */
public enum ActivateStatus {

    ACTIVATE_NO(0, "没有激活"),
    ACTIVATE_YES(1, "已经激活");

    public final int VALUE;
    public final String EXTVALUE;

    private ActivateStatus(int value, String extValue) {
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
