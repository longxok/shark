package com.cloudwalk.shark.common.utils;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {
    private Boolean success;
    private String message;
    private String code;
    private T data;

    public ResponseData(){}
    public ResponseData(Boolean success, String message, String code, T data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseData(Boolean success, String message, String code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
