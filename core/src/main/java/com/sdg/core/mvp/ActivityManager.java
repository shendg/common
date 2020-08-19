package com.sdg.core.mvp;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ActivityManager
 * Created by wangzhen on 2020/5/16.
 */
public class ActivityManager {
    private static List<Activity> sList = new ArrayList<>();

    public static void add(Activity activity) {
        if (activity != null) {
            sList.add(activity);
        }
    }

    public static void remove(Activity activity) {
        if (activity != null) {
            sList.remove(activity);
        }
    }

    public static void close(Class<?> cls) {
        for (Activity activity : sList) {
            if (activity != null && !activity.isDestroyed()) {
                if (activity.getClass() == cls) {
                    activity.finish();
                    sList.remove(activity);
                }
            }
        }
    }

    public static Activity current() {
        if (!sList.isEmpty()) {
            return sList.get(sList.size() - 1);
        }
        return null;
    }

    public static void close(Activity activity) {
        if (activity != null) {
            activity.finish();
            sList.remove(activity);
        }
    }

    public static void closeExclude(Activity activity) {
        if (activity != null) {
            Iterator<Activity> iterator = sList.iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                if (next != activity) {
                    next.finish();
                    iterator.remove();
                }
            }
        }
    }

    public static void closeAllActivities() {
        for (Activity activity : sList) {
            if (activity != null && !activity.isDestroyed()) {
                activity.finish();
            }
        }
        sList.clear();
    }
}
