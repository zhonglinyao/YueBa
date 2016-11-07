package com.lanou.yueba.dbtools;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.lanou.yueba.app.YueBaApp;
import com.lanou.yueba.bean.UserInfoBean;
import com.lanou.yueba.threadtools.ThreadTool;
import com.lanou.yueba.vlaues.StringVlaues;
import com.litesuits.orm.LiteOrm;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */

public class LiteOrmTools {
    private LiteOrm mLiteOrm;
    private Handler mHandler;

    private static final class LiteormToolsHolder {
        private static final LiteOrmTools sInstance = new LiteOrmTools();
    }


    private LiteOrmTools() {
        mLiteOrm = LiteOrm.newCascadeInstance(YueBaApp.getContext(), StringVlaues.DB_NAME);
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static LiteOrmTools getInstance() {
        return LiteormToolsHolder.sInstance;
    }

    public void insertInfo(final UserInfoBean userInfoBean) {
        ThreadTool.getInstance().executorRunnable(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(userInfoBean);
                Log.d("LiteOrmTools", "成功插入数据库");
            }
        });
    }

    public void deleteTab(final Class clazz) {
        ThreadTool.getInstance().executorRunnable(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(clazz);
            }
        });
    }

    public <T> void queryTab(final Class<T> clazz, final CallBack<T> callBack) {
        ThreadTool.getInstance().executorRunnable(new Runnable() {
            @Override
            public void run() {
                final List<T> list = mLiteOrm.query(clazz);
                mHandler.post(new HandleRunnable<T>(list, callBack));
            }
        });
    }

    class HandleRunnable<T> implements Runnable {
        private List<T> mTList;
        private CallBack<T> mCallBack;

        public HandleRunnable(List<T> TList, CallBack<T> callBack) {
            mCallBack = callBack;
            mTList = TList;
        }

        @Override
        public void run() {
            mCallBack.callBack(mTList);
        }
    }

    public interface CallBack<T> {
        void callBack(List<T> list);
    }

}
