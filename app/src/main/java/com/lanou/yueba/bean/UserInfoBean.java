package com.lanou.yueba.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by dllo on 16/10/26.
 */

public class UserInfoBean extends BmobObject{
    private String userName;
    private String md5UserPassWord;
    private String name;
    private String sex;
    private String phoneNum;
    private BmobFile pic;
    private String signature;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
