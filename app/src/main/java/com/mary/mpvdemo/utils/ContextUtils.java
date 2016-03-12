package com.mary.mpvdemo.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * File Name:   ContextUtils
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description:
 * Change log:
 * 16/3/12-19-57---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public class ContextUtils {
    /** 标识符 */
    private static final String TAG = ContextUtils.class.getSimpleName();

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param context   上下文对象
     * @param pxValue   要转化的值
     * @return  转化成的值
     */
    public static int px2sp(Context context, float pxValue) {
        // fontScale:（DisplayMetrics类中属性scaledDensity）
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param context   上下文对象
     * @param spValue   要转化的值
     * @return  转化成的值
     */
    public static int sp2px(Context context, float spValue) {
       final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
       return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * dip转换px
     * @param context   上下文对象
     * @param dipValue  要转化的 dip
     * @return  转化的值
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private static LayoutInflater inflater;
    public static View inflate(Context context, int res) {
        if (null == inflater) {
            inflater = LayoutInflater.from(context);
        }
        return inflater.inflate(res, null);
    }

    /**
     * 获取屏幕的宽
     * @param context   上下文对象
     * @return  屏幕的宽
     */
    public static int getSreenWidth(Context context) {
        // since SDK_INT = 1;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;

        // since 14 <= SDK_INT <= 17;
        if (14 <= Build.VERSION.SDK_INT && 17 >= Build.VERSION.SDK_INT) {
            try {
                width  = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
            } catch (Exception e) {
                Log.e(TAG, "GET SCREEN ERROR(14-17)：" + e.toString());
            }
        }

        // since SDK_INT <= 17;
        if (17 <= Build.VERSION.SDK_INT) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(display, point);
                width  = point.x;
            } catch (Exception e) {
                Log.e(TAG, "GET SCREEN ERROR(>=17)：" + e.toString());
            }
        }
        return width;
    }

    /**
     * 获取屏幕的高
     * @param context   上下文对象
     * @return  屏幕的高
     */
    public static int getSreenHeight(Context context) {
        // since SDK_INT = 1;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int height = metrics.heightPixels;

        // since 14 <= SDK_INT < 17;
        if (14 <= Build.VERSION.SDK_INT && 17 > Build.VERSION.SDK_INT) {
            try {
                height  = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) {
                Log.e(TAG, "GET SCREEN ERROR(14-17)：" + e.toString());
            }
        }

        // since SDK_INT <= 17;
        if (17 <= Build.VERSION.SDK_INT) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(display, point);
                height  = point.y;
            } catch (Exception e) {
                Log.e(TAG, "GET SCREEN ERROR(>=17)：" + e.toString());
            }
        }
        return height;
    }

    /**
     * 获取屏幕的宽度和高度
     * @param context 当前上下文
     * @return	当前屏幕宽和高的int[]数组index：0 --> Width	index:1 --> Height
     */
    public static int[] getScreenSize(Context context) {
        // since SDK_INT = 1;
        int[] size = new int[2];
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int widthPixels  = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        // since 14 <= SDK_INT < 17;
        if (14 <= Build.VERSION.SDK_INT && 17 > Build.VERSION.SDK_INT) {
            try {
                widthPixels  = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) {
                Log.d(TAG, "GET SCREEN ERROR(14-17)：" + e.toString());
            }
        }

        // since SDK_INT <= 17;
        if (17 <= Build.VERSION.SDK_INT) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(display, point);
                widthPixels  = point.x;
                heightPixels = point.y;
            } catch (Exception e) {
                Log.d(TAG, "GET SCREEN ERROR(>=17)：" + e.toString());
            }
        }

        size[0] = widthPixels;
        size[1] = heightPixels;
        return size;
    }
}
