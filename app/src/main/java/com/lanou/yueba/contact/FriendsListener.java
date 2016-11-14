package com.lanou.yueba.contact;

import android.util.Log;

import com.hyphenate.EMContactListener;

/**
 * Created by dllo on 16/11/14.
 */

public class FriendsListener implements EMContactListener {
    @Override
    public void onContactAdded(String s) {

    }

    @Override
    public void onContactDeleted(String s) {

    }

    @Override
    public void onContactInvited(String s, String s1) {
        Log.d("FriendsListener", "你有新的好友" + s);
    }

    @Override
    public void onContactAgreed(String s) {

    }

    @Override
    public void onContactRefused(String s) {

    }
}
