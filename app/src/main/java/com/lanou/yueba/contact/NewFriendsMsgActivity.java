package com.lanou.yueba.contact;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.lanou.yueba.R;
import com.lanou.yueba.base.BaseActivity;
import com.lanou.yueba.base.lv.ListViewCommonAdapter;
import com.lanou.yueba.base.lv.ViewHolderListView;
import com.lanou.yueba.bean.FriendBean;
import com.lanou.yueba.tools.ActivityTools;
import com.lanou.yueba.tools.ToastTools;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by dllo on 16/11/7.
 */
public class NewFriendsMsgActivity extends BaseActivity implements View.OnClickListener {

    private ListView mLv;
    private List<FriendBean> mFriendBeanList;
    private ImageView back;


    @Override
    protected int setLayout() {
        return R.layout.activity_new_friends_msg;
    }

    @Override
    protected void initView() {
        mLv = bindView(R.id.lv_msg);
        back = (ImageView) findViewById(R.id.iv_back_msg);
    }

    @Override
    protected void initData() {

        back.setOnClickListener(this);

        final String username = getIntent().getStringExtra("username");
        BmobQuery<FriendBean> friendQuery = new BmobQuery<>();
        friendQuery.addWhereEqualTo("friendname", username).addWhereEqualTo("isFriend", false);
        friendQuery.findObjects(new FindListener<FriendBean>() {
            @Override
            public void done(List<FriendBean> list, BmobException e) {
                if (e == null) {
                    mFriendBeanList = list;
                    final ListViewCommonAdapter<FriendBean> adapter = new ListViewCommonAdapter<FriendBean>(NewFriendsMsgActivity.this,
                            R.layout.item_new_friends_msg, mFriendBeanList) {
                        @Override
                        protected void convert(final ViewHolderListView viewHolder, final FriendBean item, final int position) {

                            viewHolder.setText(R.id.tv_name_friend_msg, item.getUsername());
                            Button agree = viewHolder.getView(R.id.btn_agree_msg);
                            agree.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    try {
                                        EMClient.getInstance().contactManager().acceptInvitation(item.getUsername());
                                        Log.d("NewFriendsMsgActivity", "同意");
                                        updateForBmob(item);


                                        EMClient.getInstance().contactManager().acceptInvitation(item.getFriendname());

                                        FriendBean userBean = new FriendBean();
                                        userBean.setFriendname(item.getUsername());
                                        userBean.setUsername(item.getFriendname());
                                        userBean.setFriend(true);
                                        updateForBmob(userBean);

                                        mFriendBeanList.remove(position);
                                        notifyDataSetChanged();


                                    } catch (HyphenateException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });
                        }
                    };
//                    adapter.notifyDataSetChanged();
                    mLv.setAdapter(adapter);

                } else {
                    ToastTools.showShort(NewFriendsMsgActivity.this, "暂无好友申请...");
                }
            }
        });
    }

    private void updateForBmob(FriendBean friendBean) {
        friendBean.setFriend(true);

        friendBean.update(friendBean.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d("NewFriendsMsgActivity", "数据更新成功");
                } else {
                    Log.d("NewFriendsMsgActivity", "更新失败");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_msg:
                ActivityTools.deleteActivity(NewFriendsMsgActivity.this.getClass().getSimpleName());

                break;
        }
    }
}
