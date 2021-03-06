package com.cloudwalk.shark.api.dto.validate;


import com.cloudwalk.shark.common.em.SexEnums;
import com.cloudwalk.shark.config.annotation.EnumValidator;

import java.util.Date;

public class AdvertQueryForm {
    private String merchantName;

    private String merchantCode;

    private String clientCode;

    private String id;

    private Date startDate;
    @EnumValidator(value = SexEnums.class)
    private Integer sex;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
