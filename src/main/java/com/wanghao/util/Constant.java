package com.wanghao.util;

/**
 * Created by wanghao on 8/6/17.
 */
public class Constant {
    /* UserController相关 开始 */
    public static final String LOGIN_USER = "loginUser";

    public static final String REMEMBER_ME = "rememberMe";

    public static final String REGISTER_USER = "registerUser";

    public static final String FIND_PSW_USER = "findPswUser";

    public static final String RESET_PSW_USER = "resetPswUser";
    /* UserController相关 结束 */

    /* EmailTask相关 开始 */
    public static final String EMAIL_ACCOUNT = "wh15895877701@126.com";

    public static final String EMAIL_PASSWORD = "hunter17@SEU";

    public static final String ACTIVATION_BASE_URL = "http://fantasybooker.com/user/activate";

    public static final String PASSWORD_BASE_URL = "http://fantasybooker.com/user/password/reset";
    /* EmailTask相关 结束 */

    /* 分页相关 结束 */
    //前端每页展示10条数据
    public static final int PAGINATION_LIMIT_SHOW = 3;

    //后端查数据库每次100条
    public static final int PAGINATION_LIMIT = 50;

    /* 分页相关 结束 */
}
