package com.cloudwalk.shark.common.constant;


/**
 * 基础响应码
 *
 * @author: zetting
 * @date: 2018-02-01 23:08
 */
public class BusinessCode {
    //--------成功------------
    public static final BusinessCode SUCCESS = new BusinessCode("success", "操作成功");

    //--------失败------------
    public static final BusinessCode FAILD = new BusinessCode("fail", "系统异常");
    public static final BusinessCode EXCEPTION = new BusinessCode("exception", "未知异常");
    public static final BusinessCode PARAM_ILLEGAL = new BusinessCode("param_illegal", "参数不合法");
    public static final BusinessCode PARAM_EMPTY = new BusinessCode("param_empty", "入参为空");

    private String code;

    private String msg;


    public BusinessCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessCode(BusinessCode businessCode) {
        this.code = businessCode.getCode();
        this.msg = businessCode.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
