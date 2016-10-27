package com.lanou.yueba.tools;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 16/10/27.
 */

public class ActivityTools {
    private static final Map<String, Activity> ACTIVITYMAP = new HashMap<>();

    public static void addActivity(String string, Activity activity) {
        ACTIVITYMAP.put(string, activity);
    }

    public static void deleteActivity(String string) {
        ACTIVITYMAP.get(string).finish();
        ACTIVITYMAP.remove(string);
    }

    public static Activity queryActivity(String string) {
        return ACTIVITYMAP.get(string);
    }

    public static void exitApp(){
        for (String s : ACTIVITYMAP.keySet()) {
            ACTIVITYMAP.get(s).finish();
        }
    }
}
