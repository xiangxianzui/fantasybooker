package com.wanghao.service.enums;

/**
 * Created by wanghao on 8/6/17.
 */
public enum LoginMsg {
    SUCCESS_NICKNAME(0, "昵称登录成功"),
    SUCCESS_EMAIL(1, "邮箱登录成功"),
    FAIL_DB(2, "查询数据库失败"),
    FAIL_NO_USER(3, "用户不存在"),
    FAIL_PASSWORD(4, "密码不正确"),
    FAIL_NOT_ACTIVATE(5, "账户未激活");

    public final int VALUE;
    public final String EXTVALUE;

    private LoginMsg(int value, String extValue) {
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
