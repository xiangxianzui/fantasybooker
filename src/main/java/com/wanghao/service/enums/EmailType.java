package com.wanghao.service.enums;

/**
 * Created by wanghao on 8/8/17.
 */
public enum EmailType {
    REGISTER_SUCCESS(0, "注册成功激活邮件"),
    FIND_PASSWORD(1, "找回密码邮件"),
    ACTIVATION_NOTIFY(2, "提醒激活邮件");

    public final int VALUE;
    public final String EXTVALUE;

    private EmailType(int value, String extValue) {
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
