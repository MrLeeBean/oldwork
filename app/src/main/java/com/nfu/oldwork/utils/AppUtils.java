package com.nfu.oldwork.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

/**
 * Created by user on 2017/7/27.
 */

public class AppUtils {

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    public  static void call(Context context, String phone) {
        //检查拨打电话权限
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public static String str2Num(String text){
        if (TextUtils.isEmpty(text))
            return "0";
        return text;
    }

}
