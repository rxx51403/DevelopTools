package com.renxiaoxiao.developtools.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by renxiaoxiao on 2016/8/1.
 * Activity管理工具类
 */

public class AppManagerUtil {

    // activity的管理栈
    private static Stack<Activity> activityStack;

    // 创建单例对象
    private static AppManagerUtil instance;

    private AppManagerUtil() {
    }

    public static AppManagerUtil getAppManager() {
        if (instance == null) {
            instance = new AppManagerUtil();
        }
        return instance;
    }

    // 添加activity
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    // 获取当前activity实例
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    // 结束activity并移出管理栈
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls) {
        for (Activity activity: activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishAll() {
        for (Activity activity: activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }

    // 退出应用
    public void AppExit(Context context) {
        try {
            finishAll();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
