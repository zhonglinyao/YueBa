package com.lanou.yueba.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by dllo on 16/10/26.
 */

public class UserInfoBean extends BmobObject implements Serializable{
    private String userName;
    private String md5UserPassWord;
    private String phoneNum;
    private BmobFile pic;
    private String qq;
    private String signature;

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

    public BmobFile getPic() {
        return pic;
    }

    public void setPic(BmobFile pic) {
        this.pic = pic;
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
