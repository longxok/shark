package com.cloudwalk.shark.api.model;

import java.io.Serializable;

public class Role implements Serializable {
    private transient int id;

    private String roleName;

    private transient String userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
