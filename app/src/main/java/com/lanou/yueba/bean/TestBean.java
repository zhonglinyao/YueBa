package com.lanou.yueba.bean;

/**
 * Created by dllo on 16/10/21.
 */

public class TestBean {
    private String mString;
    private Boolean isCome;

    public Boolean getCome() {
        return isCome;
    }

    public void setCome(Boolean come) {
        isCome = come;
    }

    public TestBean(Boolean isCome, String string) {
        this.isCome = isCome;
        mString = string;
    }

    public String getString() {
        return mString;
    }

    public void setString(String string) {
        mString = string;
    }
}
