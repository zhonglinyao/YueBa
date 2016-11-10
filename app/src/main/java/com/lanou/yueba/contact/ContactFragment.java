package com.lanou.yueba.contact;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.hyphenate.EMContactListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.lanou.yueba.R;
import com.lanou.yueba.widget.ContactItemView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/20.
 */

public class ContactFragment extends EaseContactListFragment {

    private Map<String, EaseUser> mMap;
    private ContactItemView mContactItemView;

    @Override
    protected void initView() {
        super.initView();
        //        titleBar.setVisibility(View.GONE);
        hideTitleBar();
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.em_contacts_head, null);
        HeaderItemClickListener clickListener = new HeaderItemClickListener();
        mContactItemView = (ContactItemView) headView.findViewById(R.id.application_item);
        mContactItemView.setOnClickListener(clickListener);
        listView.addHeaderView(headView);
        registerForContextMenu(listView);

        EMClient.getInstance().contactManager().setContactListener(new FriendListener());


    }


    @Override
    protected void setUpView() {
        super.setUpView();




        EMClient.getInstance().contactManager().aysncGetAllContactsFromServer(new EMValueCallBack<List<String>>() {
            @Override
            public void onSuccess(final List<String> strings) {
                mMap = new HashMap<String, EaseUser>();
                for (String s : strings) {
                    EaseUser user = new EaseUser(s);

                    mMap.put(s, user);
                }
                setContactsMap(mMap);
                refresh();
            }

            @Override
            public void onError(int i, String s) {

            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chatId = ((EaseUser) listView.getItemAtPosition(position)).getUsername();
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, chatId);
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(intent);

            }
        });


    }

    @Override
    public void refresh() {
        super.refresh();
    }


    protected class HeaderItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.application_item:
                    //                    Toast.makeText(getContext(), "233", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), NewFriendsMsgActivity.class);
                    intent.putExtra("username",EMClient.getInstance().getCurrentUser());
                    startActivity(intent);
                    break;
            }
        }
    }


    public class FriendListener implements EMContactListener {

        @Override
        public void onContactAdded(String s) {
            Log.d("FriendListener", "收到好友邀请1");
        }

        @Override
        public void onContactDeleted(String s) {
            Log.d("FriendListener", "收到好友邀请2");
        }

        @Override
        public void onContactInvited(String s, String s1) {
            Log.d("FriendListener", "收到好友邀请3");
        }

        @Override
        public void onContactAgreed(String s) {
            Log.d("FriendListener", "收到好友邀请4");
        }

        @Override
        public void onContactRefused(String s) {
            Log.d("FriendListener", "收到好友邀请5");
        }
    }

}
