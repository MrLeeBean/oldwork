package com.nfu.oldwork.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;

/**
 * Created by Administrator on 2017-7-25.
 */

public class NetUtil {


    public static void doGet(String url, Map<String,String> params, Callback callback){
        GetBuilder builder = OkHttpUtils.get().url(url);
        if (params != null && params.size() != 0) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                builder.addParams(entry.getKey(), entry.getValue());
            }
        }
        builder.build().execute(callback);
    }

    /**
     * 基于OkHttp3的post方式异步请求
     * 返回值为RequestCall对象
     * 可以通过该对象的cancel方法取消网络请求
     *
     * @param url
     * @param params
     * @param callback
     * @return
     */
    public static RequestCall doPost(String url, Map<String,String> params,Callback callback){
        PostFormBuilder builder = OkHttpUtils.post().url(url);
        if (params != null && params.size() != 0) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                builder.addParams(entry.getKey(), entry.getValue());
            }
        }
        RequestCall call = builder.build();
        call.execute(callback);
        return call;
    }

    public static RequestCall doPost(String url,String json,Callback callback){
        PostStringBuilder builder = OkHttpUtils.postString().url(url);
        RequestCall call = builder.content(json).mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build();
        call.execute(callback);
        return call;
    }
}
