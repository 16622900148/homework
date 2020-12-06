package com.example.homework4.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Create By Jason on 2020/10/21
 */
public class CommonUtil {

    private CommonUtil(){

    }

    /**
     * 显示键盘
     **/
    public static void showKeyBoard(Context context, final View paramEditText) {
//        paramEditText.requestFocus();
//        paramEditText.post(new Runnable() {
//            @Override
//            public void run() {
//                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(paramEditText, 0);
//            }
//        });
    }

    /**
     * 隐藏软键盘
     *
     * @param context
     * @param paramEditText
     */
    public static void hideKeyBoard(Context context, final View paramEditText) {
        InputMethodManager manager = ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE));
        if (manager != null)
            manager.hideSoftInputFromWindow(paramEditText.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
