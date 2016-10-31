package com.lanou.yueba.vlaues;

import android.util.Log;

/**
 * Created by dllo on 16/10/25.
 */

public class UrlValues {
    private static final String NEWS = "http://c.3g.163.com/nc/article/list/T1348648517839/";
    public static final String VIDEO = "http://m.live.netease.com/bolo/api/rank/hotVideo.htm";

    public static String getNews(int num) {
        int end = num + 20;
        Log.d("UrlValues", NEWS + num + "-" + end + ".html");
        return NEWS + num + "-" + end + ".html";
    }
}
