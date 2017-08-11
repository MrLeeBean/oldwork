package com.nfu.oldwork.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.nfu.oldwork.NfuApplication;
import com.nfu.oldwork.utils.DeviceUtil;


/**
 * Created by Administrator on 2017-7-3.
 */

public class NfuResource {
    private static SharedPreferences livePreferences;
    private static SharedPreferences.Editor editor;
    private final static String LIVE_SP = "nfu_live_sp";
    private final static String USE_4G = "USE_4G";
    private Context context;

    private static class LazyHolder {
        private static final NfuResource INSTANCE = new NfuResource();
    }
    private NfuResource(){
        context = NfuApplication.context;
        livePreferences = context.getSharedPreferences(LIVE_SP, context.MODE_PRIVATE);
        editor = livePreferences.edit();
    }
    public static final NfuResource getInstance() {
        return NfuResource.LazyHolder.INSTANCE;
    }

    public void updateUse4G(boolean state){
        editor.putBoolean(USE_4G,state).commit();
    }

    public boolean isUse4G(){
        return livePreferences.getBoolean(USE_4G,true);
    }

    public boolean isUseDefPic(){
        if (DeviceUtil.isWifiConnected(context)){
            return false;
        }else {
            return !isUse4G();
        }
    }
}
