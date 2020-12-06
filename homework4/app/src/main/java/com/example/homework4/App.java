package com.example.homework4;

import android.app.Activity;
import android.app.Application;
import com.example.homework4.util.SPUtil;


public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    private static App instance;
    public SPUtil mSPUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mSPUtil = SPUtil.getInstance(this);
    }

    public static App getInstance() {
        return instance;
    }

}
