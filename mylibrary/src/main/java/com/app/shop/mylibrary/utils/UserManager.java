package com.app.shop.mylibrary.utils;

import android.content.Context;

/**
 * @anthor : 大海
 * 每天进步一点点
 * @time :   2019/12/26
 * @description :
 */


public class UserManager {

    /**
     * 判断用户是否登录
     *
     * @return
     */
    public static boolean isLogin(Context context) {

        return !StringUtil.isEmpty(SharedPreferencesUtil.getData(context, "user", "user_name", ""));

    }
}
