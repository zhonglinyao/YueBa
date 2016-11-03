package com.lanou.yueba.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/10/28.
 */

public class FriendBean extends BmobObject {

    private String username;
    private String friendname;
    private Boolean isFriend;


    public Boolean getFriend() {
        return isFriend;
    }

    public void setFriend(Boolean friend) {
        isFriend = friend;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendname() {
        return friendname;
    }

    public void setFriendname(String friendname) {
        this.friendname = friendname;
    }
}
