package com.lanou.yueba.threadtools;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dllo on 16/10/24.
 */

public class ThreadTool {
    private ThreadPoolExecutor mPoolExecutor;

    private static final class ThreadToolHolder{
        private static final ThreadTool sInstance = new ThreadTool();
    }

    public static ThreadTool getInstance(){
        return ThreadToolHolder.sInstance;
    }

    private ThreadTool() {
        int CUPcore = Runtime.getRuntime().availableProcessors();
        mPoolExecutor = new ThreadPoolExecutor(
                CUPcore + 1,
                CUPcore * 2 + 1,
                60l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
    }

    public void executorRunnable(Runnable runnable){
        mPoolExecutor.execute(runnable);
    }
}
