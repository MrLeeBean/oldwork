package com.nfu.oldwork.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayManagers {
  private static DisplayManagers sInstance = new DisplayManagers();
  private float value;
  private static final int STANDARD_WIDTH = 1280;
  private static final int STANDARD_HEIGHT = 720;
  private static final int STANDARD_DENSITY = 160;

  private int screenWidth = 0;
  private int screenHeight = 0;
  private int screenDpi = 0;

  public void init(Context context) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    displayMetrics = context.getResources().getDisplayMetrics();
    screenWidth = displayMetrics.widthPixels;
    screenHeight = displayMetrics.heightPixels;
    screenDpi = displayMetrics.densityDpi;
    value = displayMetrics.scaledDensity;
    LogUtil.d("DisplayManagers , init() , screenWidth = " + screenWidth + " , screenHeight = " + screenHeight + " , screenDpi = " + screenDpi);
  }

  private DisplayManagers() {

  }

  public static DisplayManagers getInstance() {
    return sInstance;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public int getScreenDpi() {
    return screenDpi;
  }

  public int changeTextSize(int size) {
    float rate_width = (float) screenWidth / STANDARD_WIDTH;
    // float rate_height = (float) screenHeight / STANDARD_HEIGHT;
    // float rate_density = (float) STANDARD_DENSITY / screenDpi;
    // return (int)(size * rate_width * rate_height * rate_density);
    // return (int)(size * rate_density);
    // return (int) (size/value);
    // return (int)(size * rate_density);
    if (value != 1.0 && STANDARD_WIDTH == screenWidth) {
      // return size;
      return (int) (size / value);
    }
    if (screenDpi == 240) {
      return size;
    }
    return (int) (size / rate_width);
  }

  public int changePaintSize(int size) {
    return changeWidthSize(size);
  }

  public int changeWidthSize(int size) {
    float rate = (float) screenWidth / STANDARD_WIDTH;
    return (int) (size * rate);
  }

  public int changeHeightSize(int size) {
    float rate = (float) screenHeight / STANDARD_HEIGHT;
    return (int) (size * rate);
  }

  public static int dip2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }
}
