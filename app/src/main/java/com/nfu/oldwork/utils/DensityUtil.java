package com.nfu.oldwork.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by magichill33 on 2017/3/27.
 */

public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        /*final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);*/
        return (int) (getRawSize(context, TypedValue.COMPLEX_UNIT_DIP,dpValue) + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static float getRawSize(Context context, int unit, float size) {
        Resources r;

        if (context == null)
            r = Resources.getSystem();
        else
            r = context.getResources();

        return TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
    }
}
