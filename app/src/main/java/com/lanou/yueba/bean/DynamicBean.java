package com.lanou.yueba.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/11/5.
 */

public class DynamicBean extends BmobObject implements Serializable,Comparable<DynamicBean> {
    private String userName;
    private String picUrl;
    private String content;
    private String imgUrl;
    private String date;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int compareTo(DynamicBean o) {
        return this.getCreatedAt().compareTo(o.getCreatedAt());
    }
}
