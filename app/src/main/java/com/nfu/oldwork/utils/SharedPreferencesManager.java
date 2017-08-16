package com.nfu.oldwork.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.nfu.oldwork.model.LocalInfo;
import com.nfu.oldwork.model.UserInfo;

import java.io.IOException;

/**
 * sharedprefrences 封装工具类
 * get,put   int、string、float等类型的数据存取
 *
 * @author Administrator
 * @version 1.0
 * @date 2016-10-9 上午10:47:39
 */
public class SharedPreferencesManager {
    private static Context sContext;
    public static SharedPreferencesManager sInstance = null;
//    private static UserInfo userInfo;
    private static LocalInfo localInfo;


    public static void createInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPreferencesManager(context);
            LogUtil.d("SharedPreferencesManager , createInstance()");
        }
    }

    private SharedPreferencesManager(Context context) {
        sContext = context;
    }

    public static String getString(String name, String key, String defValue) {
        if (sInstance != null && sContext != null) {
            return sContext.getSharedPreferences(name, Context.MODE_PRIVATE).getString(key, defValue);
        }
        return defValue;
    }

    public static int getInt(String name, String key, int defValue) {
        if (sInstance != null && sContext != null) {
            return sContext.getSharedPreferences(name, Context.MODE_PRIVATE).getInt(key, defValue);
        }
        return defValue;
    }

    public static boolean getBoolean(String name, String key, boolean defValue) {
        if (sInstance != null && sContext != null) {
            return sContext.getSharedPreferences(name, Context.MODE_PRIVATE).getBoolean(key, defValue);
        }
        return defValue;
    }

    public static boolean putString(String name, String key, String value) {
        if (sInstance != null && sContext != null) {
            return sContext.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putString(key, value).commit();
        }
        return false;
    }

    public static boolean putInt(String name, String key, int value) {
        if (sInstance != null && sContext != null) {
            return sContext.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
        }
        return false;
    }

    public static boolean putBoolean(String name, String key, boolean value) {
        if (sInstance != null && sContext != null) {
            return sContext.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
        }
        return false;
    }

    //下边是对实体类和集合转换为字符串的存储和读取操作
    public static synchronized void putUser(String name, String key, UserInfo userInfo) {

        String str = "";
        try {
            str = SerializableUtil.objToStr(userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sInstance != null && sContext != null) {
            sContext.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putString(key, str).commit();
        }


    }

    public static synchronized UserInfo getUser(String name, String key, String defValue) {
     //   if (userInfo == null) {
        UserInfo userInfo = new UserInfo();
            String str = "";
            if (sInstance != null && sContext != null) {
                str = sContext.getSharedPreferences(name, Context.MODE_PRIVATE).getString(key, defValue);
            }
            try {
                Object object = SerializableUtil.strToObj(str);
                if (object != null) {
                    userInfo = (UserInfo) object;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
      //  }
        return userInfo;
    }

    public static synchronized void saveLocalInfo(String name, String key, LocalInfo localInfoP) {

        String str = "";
        try {
            str = SerializableUtil.objToStr(localInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sInstance != null && sContext != null) {
            sContext.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putString(key, str).commit();
        }
        localInfo =localInfoP ;

    }

    public static synchronized LocalInfo getLocalInfo(String name, String key, String defValue) {
        if (localInfo == null) {
            localInfo = new LocalInfo();
            String str = "";
            if (sInstance != null && sContext != null) {
                str = sContext.getSharedPreferences(name, Context.MODE_PRIVATE).getString(key, defValue);
            }
            try {
                Object object = SerializableUtil.strToObj(str);
                if (object != null) {
                    localInfo = (LocalInfo) object;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return localInfo;
    }

}
