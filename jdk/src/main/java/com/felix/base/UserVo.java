package com.felix.base;

import java.io.Serializable;


public class UserVo implements Serializable {

    private static final long serialVersionUID = 8661690034035602034L;
    private String username;

    private transient static String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
