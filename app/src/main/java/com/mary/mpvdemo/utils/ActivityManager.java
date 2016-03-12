package com.mary.mpvdemo.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * File Name:   ActivityManager
 * Author:      Mary
 * Write Dates: 16/3/12
 * Description: 应用程序Activity管理类：用于Activity管理和应用程序退出
 * Change log:
 * 16/3/12-17-46---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public class ActivityManager {

    /** Activity 栈 */
    private static Stack<Activity> activityStack;
    /** 静态实例对象 */
    private static ActivityManager instance;
    /** 屏蔽构造方法 */
    private ActivityManager() {}

    /**
     * 获取单例实例对象
     * @return 单例实例对象
     */
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 添加一个 Activity
     * @param activity 添加的 Activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前显示的 Activity
     * @return  当前显示的 Activity
     */
    public Activity currentActivity() {
        // 根据栈结构，最上面的 Activity 就是当前显示的 Activity
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 销毁当前 Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 销毁指定 Activity
     * @param activity 要销毁的 Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 根据 cls 销毁一个 Activity
     * @param cls   cls 对象
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 销毁所有 Activity 并退出应用程序
     * @param context   上下文对象
     */
    public void finishAllActivityAndExit(Context context) {
        if (null != activityStack) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

}
