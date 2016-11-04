package com.lanou.yueba.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by dllo on 16/10/26.
 */

public class UserInfoBean extends BmobObject implements Serializable{

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String userName;
    private String md5UserPassWord;
    private String phoneNum;
    private String picUrl;
    private String qq;
    private String signature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMd5UserPassWord() {
        return md5UserPassWord;
    }

    public void setMd5UserPassWord(String md5UserPassWord) {
        this.md5UserPassWord = md5UserPassWord;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
