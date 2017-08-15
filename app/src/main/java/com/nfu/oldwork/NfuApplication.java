package com.nfu.oldwork;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;


import com.nfu.oldwork.utils.SharedPreferencesManager;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017-7-25.
 */

public class NfuApplication extends Application {
    public static Context context;

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                //其他配置

                .build();

        OkHttpUtils.initClient(okHttpClient);

        SharedPreferencesManager.createInstance(this);


    }
}
