package com.cloudwalk.shark.interview.lintCode;

import java.util.*;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.lintCode
 * @date:2019/8/26
 */
public class Solution1 {
    public static void main(String args[]) {
        System.out.println(longestIncreasingSubsequence(new int[]{5,6,1,9,11}));
        /*List<List<Integer>> list = kSumII(new int[]{1,2,3,4}, 5, 2);
        Iterator<List<Integer>> iterator = list.iterator();
        while (iterator.hasNext()) {
            List<Integer> integerList = iterator.next();
            integerList.stream().forEach(System.out::print);
            System.out.println("*************");
        }*/
    }

    public static List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        int length = A.length;
        if(length==k) {
           if(Arrays.stream(A).sum()==target){
               List<List<Integer>> list = new ArrayList<>();
                List<Integer> listArray = new ArrayList<>();
                for(int item:A){
                    listArray.add(item);
                }
                list.add(listArray);
                return list;
           }else{
               return null;
           }
        }
        Set<List<Integer>> listArrayRes = new HashSet<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(i==j) {
                    continue;
                }
                int index = k-1;
                int sum = A[i];
                int start = j;
                List<Integer> list = new ArrayList<>();
                list.add(A[i]);
                while (index != 0&&start<length&&start!=i) {
                    sum += A[start];
                    index--;
                    list.add(A[start]);
                    start++;
                }
                if (sum == target&&list.size()==k) {

                    list.sort(new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o1.compareTo(o2);
                        }
                    });
                    listArrayRes.add(list);
                }

            }
        }
        List res = new ArrayList<>();
        res.addAll(listArrayRes);
        return res;
    }
    // 5,6,1,9,11
    public static int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if (nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int[] dep = new int[length];
        dep[0] =1;
        int max = 0;
        for(int i=1;i<length;i++){
            int maxval = 0;
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    maxval = Math.max(maxval, dep[j]);
                }
            }
            dep[i] = maxval + 1;
            max = Math.max(max,dep[i]);
        }

       return max;
    }


    public static int longestCommonSubstring(String A, String B) {
        // write your code here
        char[] charArraySource = A.toCharArray();
        char[] charArrayTarget = B.toCharArray();
        int max = 0;
        for (int i = 0; i < charArraySource.length; i++) {
            int tempMax = 0;
            for (int j = 0, k = i; j < charArrayTarget.length && k < charArraySource.length; j++) {
                if (charArraySource[k] == charArrayTarget[j]) {
                    tempMax++;
                    k++;
                } else {
                    if (max < tempMax) {
                        max = tempMax;
                    }
                    k = i;
                    tempMax = 0;
                }
            }
            if (max < tempMax) {
                max = tempMax;
            }
        }
        return max;
    }
}
