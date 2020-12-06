package com.example.homework4.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Create By Jason on 2020/9/10
 */
public class SPUtil {

    private static final String TAG = SPUtil.class.getSimpleName();
    private static final String tag = "HomeworkSP";
    public static final String REMEMBER_PASSWORD = "remember_password";

    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "PASSWORD";

    private static SharedPreferences mSharedPreferences;
    private static SPUtil mSPUtil;

    private SPUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
    }

    public static synchronized SPUtil getInstance(Context context) {
        if (mSPUtil == null)
            mSPUtil = new SPUtil(context);
        return mSPUtil;
    }

    public void putString(String key, String value) {
        if (mSPUtil != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public String getString(String key) {
        return getStringValue(key, "");
    }

    public String getStringValue(String key, String oppositeValue) {
        String value = "";
        if (mSharedPreferences != null) {
            value = mSharedPreferences.getString(key, oppositeValue);
        }
        return value;
    }

    public void putInt(String key, int value) {
        if (mSPUtil != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public int getInt(String key) {
        return getIntValue(key, 0);
    }

    public int getIntValue(String key, int oppositeValue) {
        int value = 0;
        if (mSharedPreferences != null) {
            value = mSharedPreferences.getInt(key, oppositeValue);
        }
        return value;
    }

    public void pubBoolean(String key, boolean value) {
        if (mSharedPreferences != null) {
            mSharedPreferences.edit().putBoolean(key, value).commit();
        }
    }

    public boolean getBoolean(String key) {
        boolean value = false;
        if (mSharedPreferences != null) {
            value = mSharedPreferences.getBoolean(key, false);
        }
        return value;
    }

    public boolean getBoolean(String key, boolean oppositeValue) {
        boolean value = false;
        if (mSharedPreferences != null) {
            value = mSharedPreferences.getBoolean(key, oppositeValue);
        }
        return value;
    }

    public void clearData() {
        if (mSPUtil != null) {
            mSharedPreferences.edit().clear().commit();
        }
    }
}
