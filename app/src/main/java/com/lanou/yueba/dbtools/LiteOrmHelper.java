package com.lanou.yueba.dbtools;

import com.lanou.yueba.bean.UserInfoBean;

/**
 * Created by dllo on 16/11/16.
 */

public class LiteOrmHelper {

    private static final class LiteOrmHelperHolder{
        private static final LiteOrmHelper sInstance = new LiteOrmHelper();
    }

    public LiteOrmHelper getInstance(){
        return LiteOrmHelperHolder.sInstance;
    }

    public void insertInfo(UserInfoBean userInfoBean){
        LiteOrmTools.getInstance().insertInfo(userInfoBean);
    }

    public void deleteTab(Class clazz){
        LiteOrmTools.getInstance().deleteTab(clazz);
    }

    public <T> void queryTab(Class<T> clazz, LiteOrmTools.CallBack callBack){
        LiteOrmTools.getInstance().queryTab(clazz, callBack);
    }

}
