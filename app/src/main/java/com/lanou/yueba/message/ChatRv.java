package com.lanou.yueba.message;

import android.content.Context;

import com.lanou.yueba.base.rv.MultiItemTypeAdapter;
import com.lanou.yueba.bean.FromBean;
import com.lanou.yueba.bean.SendBean;
import com.lanou.yueba.bean.TestBean;

import java.util.List;

/**
 * Created by dllo on 16/10/21.
 */

public class ChatRv extends MultiItemTypeAdapter<TestBean> {

    public ChatRv(Context context, List<TestBean> datas) {
        super(context, datas);
        addItemViewDelegate(0, new SendBean());
        addItemViewDelegate(1, new FromBean());
    }
}
