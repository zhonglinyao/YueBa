package com.lanou.yueba.message;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.View;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.lanou.yueba.contact.ChatActivity;

/**
 * Created by dllo on 16/10/20.
 */

public class MessageFragment extends EaseConversationListFragment {


    @Override
    protected void initView() {
        super.initView();

        hideTitleBar();

    }

    @Override
    protected void setUpView() {
        super.setUpView();

        setConversationListItemClickListener(new EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                String chatId = conversation.getUserName();
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, chatId);
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onConnectionDisconnected() {
        super.onConnectionDisconnected();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
