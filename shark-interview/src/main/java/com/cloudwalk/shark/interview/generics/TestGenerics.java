package com.cloudwalk.shark.interview.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.generics
 * @date:2019/7/8
 */
public class TestGenerics {
    public static void main(String args[]){
        List<String> l1 = new ArrayList<String>();
        List<Integer> l2 = new ArrayList<Integer>();
        /**
         * 上面的代码中涉及到了泛型，而输出的结果缘由是类型擦除。先好好说说泛型。
         */
        System.out.println(l1.getClass() == l2.getClass());

    }
}
