package com.cloudwalk.shark.interview.algorithm.interview;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.algorithm.interview
 * @date:2019/8/14
 */

/**
 * 例如，当从字符流中只读出前两个字符“go”时，第一个只出现一次的字符是‘g’。
 * 当从该字符流中读出前六个字符“google”时，第一个只出现1次的字符是”l”。
 */
public class Test1 {
    public static void main(String args[]){
       System.out.println(getOnlyOnceChar("asdfqweda"));
    }
    public static String getOnlyOnceChar(String string){
        if(string==null||string.length()==0) {
            return null;
        }

        else{
            char[] charArray = string.toCharArray();
            for(int i=0;i<charArray.length;i++){
                int hitFlag=0;
                for(int j=0;j<charArray.length;j++){
                    if(j==i) continue;
                    if(charArray[i]==charArray[j]){
                        hitFlag ++;
                        if(hitFlag >1){
                            break;
                        }
                    }
                }
                if(hitFlag==0) {
                    String s = String.valueOf(charArray[i]);
                    return s;
                }
            }
        }
        return null;
    }
}
