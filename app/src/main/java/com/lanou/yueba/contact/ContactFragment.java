package com.lanou.yueba.contact;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/20.
 */

public class ContactFragment extends EaseContactListFragment {

    private Map<String, EaseUser> mMap;

    @Override
    protected void initView() {
        super.initView();
//        titleBar.setVisibility(View.GONE);
        hideTitleBar();



    }

    @Override
    protected void setUpView() {



        EMClient.getInstance().contactManager().aysncGetAllContactsFromServer(new EMValueCallBack<List<String>>() {
            @Override
            public void onSuccess(final List<String> strings) {
                mMap = new HashMap<String, EaseUser>();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (String s : strings) {
                            EaseUser user = new EaseUser(s);
                            mMap.put(s,user);
                        }
                        setContactsMap(mMap);
                        refresh();
                    }
                });

            }
            @Override
            public void onError(int i, String s) {

            }
        });
        super.setUpView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chatId = ((EaseUser)listView.getItemAtPosition(position)).getUsername();
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
}
