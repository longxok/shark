package com.cloudwalk.shark.interview.lintCode;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.lintCode
 * @date:2019/8/26
 */
public class Solution {
    /**
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public static String rotateString(char[] str, int offset) {
        // write your code here
        if(offset>0){
            if(offset>str.length){
                offset = offset %str.length;
            }
            int length = str.length;
            char [] charArray = new char[length];
            for(int i=length-offset,j=0;i<length;i++,j++){
                charArray[j] = str[i];
            }
            System.out.println(new String(charArray));
            for(int i=0,j=offset;i<length-offset;i++,j++){
                charArray[j] = str[i];
            }
           return new String(charArray);
        }
        return new String(str);
    }

    public static int singleNumber(int[] nums) {

        if (nums == null || nums.length < 1) {
            throw new IllegalArgumentException("nums");
        }


        for (int i = 1; i< nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    public static void main(String args[]){
        System.out.println(singleNumber(new int[]{3,12,12}));

        System.out.println(rotateString(new char[]{'a','b','c','d','e','f','g'},3));
    }
}
