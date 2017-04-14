package com.hr.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hr.utils.text.StringUtil;


/**
 * SharePreference工具类
 * Created by zhangjutao on 16/7/4.
 */
public class SpUtil {
    private static final String SP_NAME = "hr_zhengda";

    public static void putString(String key, String value) {
        if (!StringUtil.isEmpty(value))
            getSharedPreferences().edit().putString(key, value).apply();
    }

    public static void remove(String key) {
        getSharedPreferences().edit().remove(key).apply();
    }

    public static void putString(String key, String value, boolean isAllowNull) {
        if (isAllowNull) {
            getSharedPreferences().edit().putString(key, value).apply();
        } else {
            putString(key, value);
        }
    }

    public static String getString(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    public static void putInt(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).commit();
    }

    public static int getInt(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }

    private static SharedPreferences getSharedPreferences() {
        return UIUtil.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
}
