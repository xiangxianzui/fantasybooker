package com.wanghao.service.enums;

/**
 * Created by wanghao on 8/5/17.
 */
public enum RegisterMsg {
    SUCCESS(0, "注册成功"),
    FAIL_DB(1, "查询数据库失败"),
    FAIL_NICK(2, "该昵称已被注册"),
    FAIL_EMAIL(3, "该邮箱已被注册");

    public final int VALUE;
    public final String EXTVALUE;

    private RegisterMsg(int value, String extValue) {
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
