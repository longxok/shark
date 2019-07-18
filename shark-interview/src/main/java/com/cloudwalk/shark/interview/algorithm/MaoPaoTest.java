package com.cloudwalk.shark.interview.algorithm;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.algorithm
 * @date:2019/7/18
 */
public class MaoPaoTest {
    private int[] initArray = new int[]{1,12,22,33,142,3,23};

    private int[] sortArray(int[] initArray){
        if(initArray ==null ||initArray.length==0){
            return null;
        }

        for(int i=0;i<initArray.length;i++){
            for(int j=i+1;j<initArray.length;j++){
                int temp = initArray[i];
                if(initArray[i]<initArray[j]){
                    initArray[i] = initArray[j];
                    initArray[j] = temp;
                }
            }
        }
        return initArray;
    }

    private void print(int[] initArray){
        for(int i: initArray){
            System.out.printf(i+" ");
        }
    }

    public static void main(String args[]){
        MaoPaoTest maoPaoTest = new MaoPaoTest();
        int[] resultArray = maoPaoTest.sortArray(maoPaoTest.initArray);
        maoPaoTest.print(resultArray);
    }


}
