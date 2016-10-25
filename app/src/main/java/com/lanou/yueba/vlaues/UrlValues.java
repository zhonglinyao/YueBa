package com.lanou.yueba.vlaues;

/**
 * Created by dllo on 16/10/25.
 */

public class UrlValues {
    public static final String NEWS = "http://wireless.tianya.cn/v/cms/getFocusByDay?sectionIds=22254&date=";

    public static String getNEWS(String date) {
        return NEWS + date;
    }
}
