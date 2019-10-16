package com.cloudwalk.shark.interview.inter;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.inter
 * @date:2019/8/28
 */
public interface FatherInterface {
    void say();
}

class Son extends Father{

    @Override
    public void say() {

    }
}

 class Father implements FatherInterface{

     @Override
     public void say() {

     }
 }
