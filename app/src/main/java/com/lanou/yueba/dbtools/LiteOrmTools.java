package com.lanou.yueba.dbtools;

import com.lanou.yueba.app.YueBaApp;
import com.lanou.yueba.vlaues.StringVlaues;
import com.litesuits.orm.LiteOrm;

/**
 * Created by dllo on 16/10/24.
 */

public class LiteOrmTools {
    private LiteOrm mLiteOrm;

    private static final class LiteormToolsHolder {
        private static final LiteOrmTools sInstance = new LiteOrmTools();
    }

    public LiteOrmTools() {
        mLiteOrm = LiteOrm.newCascadeInstance(YueBaApp.getContext(), StringVlaues.DBName);

    }
}
