package com.lanou.yueba.main;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/10/22.
 */

public class YueBaApp extends Application{
    private Context mContext;


    public Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }
}
