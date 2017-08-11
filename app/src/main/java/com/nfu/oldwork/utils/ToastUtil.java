package com.nfu.oldwork.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class ToastUtil {

    private static Toast mToast;

    public static void showShortToast(Context context, String message) {
        if(mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showShortToast(Context context, @StringRes int message) {
        if(mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLongToast(Context context, String message) {
        if(mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public static void showLongToast(Context context, @StringRes int message) {
        if(mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }
}