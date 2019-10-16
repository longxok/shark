package com.cloudwalk.shark.interview.fatherson;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.fatherson
 * @date:2019/8/20
 */
public class Test {
    public static void main(String args[]){
        Son son = new Son();
        son.setReturnCode(1);
        System.out.println(son.isSuccess());
    }
}
