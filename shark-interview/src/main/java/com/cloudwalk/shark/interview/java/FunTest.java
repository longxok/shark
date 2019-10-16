package com.cloudwalk.shark.interview.java;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.java
 * @date:2019/7/31
 */
public class FunTest {
    public static void main(String[] args) {

        char[] charArray = new char[]{'a','b','c','d'};

        StringBuilder sb = new StringBuilder("123213");
        System.out.println(noChangeStringBuilder(sb));
        System.out.println(replaceChar(charArray));
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("name", "June");
                put("age", 12);
            }
        };

        int i=1;int j;j=i++;
        System.out.println("i="+i+";j="+j);
        System.out.println(map.get("age"));
        Byte b = 11;
        Integer b2 = 11;
        System.out.println(b.equals(b2));
    }

    public static char[] replaceChar(char[] charArray){
        for(int i=0;i<charArray.length;i++){
            if(i==2){
                charArray[i]='x';
            }
        }
        return charArray;
    }

    public static StringBuilder changeStringBuilder(StringBuilder stringBuilder){
        stringBuilder.append("aaaa");
        return stringBuilder;
    }

    public static StringBuilder noChangeStringBuilder(StringBuilder stringBuilder){
        stringBuilder = new StringBuilder();
        return stringBuilder.append("aaaaaa");
    }


}
