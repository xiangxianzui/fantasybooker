package com.wanghao.service.enums;

/**
 * Created by wanghao on 8/8/17.
 */
public enum FindPswMsg {
    SUCCESS(0, "重置密码成功"),
    SUCCESS_EMAIL(1, "发送找回密码邮件"),
    FAIL_PARAMS(2, "请求参数错误"),
    FAIL_NO_USER(3, "用户不存在"),
    FAIL_NOT_MATCH(4, "name和code不匹配");

    public final int VALUE;
    public final String EXTVALUE;

    private FindPswMsg(int value, String extValue) {
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
