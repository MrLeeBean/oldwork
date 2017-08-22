package com.nfu.oldwork;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;


import com.nfu.oldwork.config.NfuResource;
import com.nfu.oldwork.model.UserInfo;
import com.nfu.oldwork.utils.SharedPreferencesManager;
import com.tencent.bugly.crashreport.CrashReport;
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
        CrashReport.initCrashReport(getApplicationContext(), "64618000fa", true);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                //其他配置

                .build();

        OkHttpUtils.initClient(okHttpClient);

        SharedPreferencesManager.createInstance(this);
        UserInfo user = SharedPreferencesManager.getUser("userinfo", "UserInfo", "");
        if( user.isLoginSuccess()){
            NfuResource.isLoginSuccess = true;
        }else {
            NfuResource.isLoginSuccess = false;
        }

    }
}
