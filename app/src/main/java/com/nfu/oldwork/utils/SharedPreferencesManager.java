package com.nfu.oldwork.utils;

import android.content.Context;

/**
 * sharedprefrences 封装工具类 
 *  get,put   int、string、float等类型的数据存取 
 * @author Administrator
 * @version 1.0 
 * @date 2016-10-9 上午10:47:39
 */
public class SharedPreferencesManager {
	private static Context sContext;
	public static SharedPreferencesManager sInstance  = null;
	public static void createInstance(Context context) {
		if(sInstance == null ){
			sInstance = new SharedPreferencesManager(context);
			 LogUtil.d("SharedPreferencesManager , createInstance()");
		}
	}
	private SharedPreferencesManager(Context context) {
		sContext = context;
	}
	
	public static String getString(String name ,String key ,String defValue){
		if(sInstance != null && sContext != null){
			return  sContext.getSharedPreferences(name, Context.MODE_PRIVATE).getString(key, defValue);
		}
		return defValue;
	}
	public static int getInt(String name ,String key ,int defValue){
		if(sInstance != null && sContext != null){
			return  sContext.getSharedPreferences(name, Context.MODE_PRIVATE).getInt(key, defValue);
		}
		return defValue;
	}
	public static boolean getBoolean(String name ,String key ,boolean defValue){
		if(sInstance != null && sContext != null){
			return  sContext.getSharedPreferences(name, Context.MODE_PRIVATE).getBoolean(key, defValue);
		}
		return defValue;
	}
	
	public static boolean putString(String name ,String key ,String value){
		if(sInstance != null && sContext != null){
			return  sContext.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putString(key, value).commit();
		}
		return false;
	}
	public static boolean putInt(String name ,String key ,int value){
		if(sInstance != null && sContext != null){
			return  sContext.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
		}
		return false;
	}
	public static boolean putBoolean(String name ,String key ,boolean value){
		if(sInstance != null && sContext != null){
			return  sContext.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
		}
		return false;
	}
	
}
