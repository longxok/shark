package com.cloudwalk.shark.model;

import com.cloudwalk.shark.encrypt.EncryptField;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int id;

    private String userName;
    @EncryptField
    private String score;

    private boolean kickout;

    private List<Role> roleList;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    public boolean isKickout() {
        return kickout;
    }

    public void setKickout(boolean kickout) {
        this.kickout = kickout;
    }

}
