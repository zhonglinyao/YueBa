package com.lanou.yueba.dynamic.live;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.activity.BaseActivity;
import com.lanou.yueba.base.recyclerview.CommonRecyclerAdapter;
import com.lanou.yueba.base.recyclerview.MultiItemTypeRecyclerAdapter;
import com.lanou.yueba.base.recyclerview.ViewHolder;
import com.lanou.yueba.tools.ActivityTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/1.
 */

public class LiveListActivity extends BaseActivity{

    private ImageView mIvBack;
    private RecyclerView mRv;
    private static String[] liveList = new String[] {
            "CCTV1综合","CCTV2财经","CCTV3综艺","CCTV4中文国际","CCTV5体育","CCTV6电影","CCTV7军事农业","CCTV8电视剧", "CCTV9纪录",
            "CCTV10科教","CCTV11戏曲","CCTV12社会与法","CCTV13新闻","CCTV14少儿","CCTV15音乐","第一剧场","湖南卫视"
    };
    private static String [] liveUrlList = new String[]{
            "http://58.135.196.138:8090/live/db3bd108e3364bf3888ccaf8377af077/index.m3u8",
            "http://58.135.196.138:8090/live/e31fa63612644555a545781ea32e66d4/index.m3u8",
            "http://58.135.196.138:8090/live/A68CE6833D654a9e932A657689463088/index.m3u8",
            "http://58.135.196.138:8090/live/56383AB184D54ac8B20478B6A43906DC/index.m3u8",
            "http://58.135.196.138:8090/live/6b9e3889ec6e2ab1a8c7bd0845e5368a/index.m3u8",
            "http://58.135.196.138:8090/live/6132e9cb136050bd94822db31d1401af/index.m3u8",
            "http://58.135.196.138:8090/live/a9a97bed07eca008a1c88c9d7c74965d/index.m3u8",
            "http://58.135.196.138:8090/live/6e38d69416f160bad4657788d6c06c01/index.m3u8",
            "http://58.135.196.138:8090/live/557a950d2cfcf2ee1aad5a260893c2b8/index.m3u8",
            "http://58.135.196.138:8090/live/a0effe8f31af0011b750e817c1b6e8c7/index.m3u8",
            "http://58.135.196.138:8090/live/2D5B4B7AB5A14d79A0D9A1D37540E2BF/index.m3u8",
            "http://58.135.196.138:8090/live/3720126fbea745f74c1c89df9797caac/index.m3u8",
            "http://58.135.196.138:8090/live/5e02ac2a0829f7ab10d6543fdd211d8e/index.m3u8",
            "http://58.135.196.138:8090/live/4A5DF0BA0C994081B02F8215491C4E48/index.m3u8",
            "http://58.135.196.138:8090/live/ADBD55B50F2D47bb970DCCBAF458E6C8/index.m3u8",
            "http://58.135.196.138:8090/live/13BCA1A2EFDB4db3B5DF6A8D343C3E39/index.m3u8",
            "http://117.144.248.49/HDhnws.m3u8?authCode=07110409322147352675&amp;stbId=006001FF0018120000060019F0D496A1&amp;Contentid=6837496099179515295&amp;mos=jbjhhzstsl&amp;livemode=1&amp;channel-id=wasusyt"
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_live_list;
    }

    @Override
    protected void initView() {
        mIvBack = bindView(R.id.iv_back_livelist);
        mRv = bindView(R.id.rv_livelist);
    }

    @Override
    protected void initData() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTools.deleteActivity(LiveListActivity.this.getClass().getSimpleName());
            }
        });
        final List<String> strings = new ArrayList<>();
        for (String s : liveList) {
            strings.add(s);
        }
        CommonRecyclerAdapter<String> adapter = new CommonRecyclerAdapter<String>(this,R.layout.layout_live, strings) {
            @Override
            protected void convert(ViewHolder holder, String string, int position) {
                holder.setText(R.id.tv_list_live, string);
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(LiveListActivity.this, LiveActivity.class);
                intent.putExtra(LiveActivity.LIVE_URL, liveUrlList[position]);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        ActivityTools.deleteActivity(LiveListActivity.this.getClass().getSimpleName());
    }
}
