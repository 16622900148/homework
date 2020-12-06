package com.example.homework4.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Create By Jason on 2020/10/16
 */
public class ToastUtil {

    private static Toast mToast;
    private static Toast mmToast;
    private static Toast mmcToast;

    public ToastUtil() {
    }

    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, 0);
        } else {
            mToast.setText(text);
            mToast.setDuration(0);
        }

        mToast.show();
    }

    public static void showMidToast(Context context, String text) {
        if (mmToast == null) {
            mmToast = Toast.makeText(context, text, 0);
            mmToast.setGravity(17, 0, 0);
        } else {
            mmToast.setText(text);
            mmToast.setDuration(0);
        }

        mmToast.show();
    }

    public static void showCostomerMidToast(Context context, View view, String text) {
        if (mmcToast == null) {
            mmcToast = Toast.makeText(context, text, 0);
            mmcToast.setGravity(17, 0, 0);
            ((ViewGroup)mmcToast.getView()).addView(view, 0);
        } else {
            mmcToast.setText(text);
            mmcToast.setDuration(0);
        }

        mmcToast.show();
    }

}
