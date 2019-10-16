package com.cloudwalk.shark.interview.algorithm.TreeTest;

import java.util.Arrays;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.algorithm.TreeTest
 * @date:2019/8/7
 */
public class SelectSortTest {
    public static void main(String args[]) throws Exception {
        System.out.println((int) Math.floor(4 / 2));
        int[] intArray = new int[]{22, 12, 1, 2, 3, 44, 5};
        display(intArray);

        display(sortHeap(intArray));
    }

    /**
     * 遍历打印
     */
    public static void display(int[] list) {
        if (list != null && list.length > 0) {
            for (int num :
                    list) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }




        public static int[] sortHeap(int[] sourceArray) throws Exception {
            // 对 arr 进行拷贝，不改变参数内容
            int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
            int len = arr.length;
            buildMaxHeap(arr, len);
            for (int i = len - 1; i > 0; i--) {
                swap(arr, 0, i);
                len--;
                heapify(arr, 0, len);
            }
            return arr;
        }

    public static void buildMaxHeap(int[] arr, int len) {
            for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
                heapify(arr, i, len);
            }
        }

    public static void heapify(int[] arr, int i, int len) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;
            if (left < len && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < len && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(arr, i, largest);
                heapify(arr, largest, len);
            }
        }

    public static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }


    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return null;
        }
        for (int i = 0; i < array.length; i++) {

            int min = array[i];
            int swapIndex = 0;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    swapIndex = j;
                }
            }
            if (min != array[i]) {
                int temp = array[i];
                array[i] = min;
                array[swapIndex] = temp;
            }
        }
        return array;
    }
}
