package com.lanou.yueba.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dllo on 16/11/1.
 */

public class TimeTools {

    public static String getDateFormate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(c.getTime());
    }
}
