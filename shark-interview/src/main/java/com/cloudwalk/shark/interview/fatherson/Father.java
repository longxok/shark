package com.cloudwalk.shark.interview.fatherson;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.fatherson
 * @date:2019/8/20
 */
public class Father {

    private int returnCode;

    public Boolean isSuccess(){
        return this.returnCode == 0;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
