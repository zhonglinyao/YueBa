package com.lanou.yueba.dynamic.video;

import android.support.v7.widget.RecyclerView;

import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.lv.ListViewCommonAdapter;
import com.lanou.yueba.base.rv.CommonRecyclerAdapter;
import com.lanou.yueba.base.rv.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p/>
 * Created by 程洪运 on 16/10/24.
 */
public class VideoActivity extends BaseActivity{
    private RecyclerView mRVVideo;
    private ListViewCommonAdapter<VideoBean> mAdapter;
    private List<VideoBean> mVideoBeen = new ArrayList<>();
    @Override
    protected int setLayout() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        mRVVideo = bindView(R.id.rv_video);
    }

    @Override
    protected void initData() {

        mRVVideo.setAdapter(new CommonRecyclerAdapter<>() {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        });

//        mRVVideo.setAdapter
//                (mAdapter = new ListViewCommonAdapter<VideoBean>
//                        (getApplicationContext(), R.layout.item_video, mVideoBeen) {
//            @Override
//            protected void convert(ViewHolderListView viewHolder, VideoBean item, int position) {
//                RelativeLayout relativeLayout = viewHolder.getView(R.id.item_rl_video);
//                if (relativeLayout != null){
//                    RelativeLayout.LayoutParams layoutParams = (LayoutParams) relativeLayout.getLayoutParams();
//                    layoutParams.height = (int) (SuperPlayerUtils.getScreenWidth((Activity) mContext) * 0.5652f);
//                    relativeLayout.setLayoutParams(layoutParams);
//                }
//                Glide.with(VideoActivity.this).load(item.getAvatar()).into((ImageView) viewHolder.getView(R.id.item_avatar_video));
//                viewHolder.setText(R.id.item_channelName_video, item.getChannelName());
//                viewHolder.setMax(R.id.item_playCount_video, item.getPlayCount());
//            }
//        });

    }
}
