package com.wanghao.service.enums;

/**
 * Created by wanghao on 8/7/17.
 */
public enum ActivateMsg {
    SUCCESS(0, "激活成功"),
    FAIL_REPEAT(1, "请勿重新激活"),
    FAIL_PARAMS(2, "请求参数错误"),
    FAIL_NOT_MATCH(3, "name和code不匹配");

    public final int VALUE;
    public final String EXTVALUE;

    private ActivateMsg(int value, String extValue) {
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
