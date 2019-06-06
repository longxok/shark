package com.cloudwalk.shark.common.em;

public enum GlobalCodeEnum {

    SUCCESS("","000000","success"),
    FAIL("","999999","success");

    private String moduleName;
    private String errorCode;
    private String errorMessage;

    GlobalCodeEnum(String moduleName, String errorCode, String errorMessage) {
        this.moduleName = moduleName;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
