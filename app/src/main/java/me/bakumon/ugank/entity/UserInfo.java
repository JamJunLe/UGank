package me.bakumon.ugank.entity;

import org.litepal.crud.DataSupport;

/**
 * UserInfo
 * Created by bakumon on 2017/2/18
 */
public class UserInfo extends DataSupport {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
