package com.nfu.oldwork.model;

import java.io.Serializable;

/**
 * Created by user on 2017/8/14.
 */

public class LoginInfo implements Serializable{

    public int signKey;
    public String loginid;
    public String password;

    public int getSignKey() {
        return signKey;
    }

    public void setSignKey(int signKey) {
        this.signKey = signKey;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "signKey=" + signKey +
                ", loginid='" + loginid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
