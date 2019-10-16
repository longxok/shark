package com.cloudwalk.shark.interview.algorithm.interview;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.algorithm.interview
 * @date:2019/8/14
 */

/**
 * input:hwwhhhuuuwello
 * output:h1w2h3u3w1e1l2o1
 */
public class Test3 {
    public static void main(String args[]){
        System.out.println(getCharCount("hwwhhhuuuwello"));
    }

    public static String getCharCount(String str){
        char[] charrArray = str.toCharArray();
        StringBuffer buffer = new StringBuffer();
        int cursor = 1;
        for(int i=0;i<charrArray.length;i=i+cursor){
            buffer.append(charrArray[i]);
            int count =1;
            for(int j=i+1;j<charrArray.length;j++){
                if(charrArray[i] ==charrArray[j] ){
                    count++;
                }else{
                    break;
                }
            }
            cursor =count;
            buffer.append(count);
        }
        return buffer.toString();
    }
}
