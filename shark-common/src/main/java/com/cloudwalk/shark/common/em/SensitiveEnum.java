package com.cloudwalk.shark.common.em;

public enum SensitiveEnum {
    /**
     *
     */
    BANK_NO("bankCardNo","bankCardNoHide","银行卡号"),
    /**
     *
     */
    PHONE("phone","phoneOrTelNoHide","手机号"),
    /**
     *
     */
    EMAIL("email","emailHide","邮箱"),

    /**
     *
     */
    ID_CARD("idCard","idCardNoHide","身份证号");
    private String keyName;

    private String sensitiveMethod;
    private String comment;

    /**
     *
     * @param keyName
     * @param comment
     */
    SensitiveEnum(String keyName,String comment){
        this.keyName = keyName;
        this.comment = comment;
    }

    SensitiveEnum(String keyName,String sensitiveMethod,String comment){
        this.keyName = keyName;
        this.sensitiveMethod = sensitiveMethod;
        this.comment = comment;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getSensitiveMethod() {
        return sensitiveMethod;
    }

    public void setSensitiveMethod(String sensitiveMethod) {
        this.sensitiveMethod = sensitiveMethod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
