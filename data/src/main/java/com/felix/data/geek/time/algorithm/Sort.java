package com.felix.data.geek.time.algorithm;

import org.apache.commons.lang3.ArrayUtils;

public class Sort {

    /**
     * 冒泡排序
     * @param arr
     */
    public static void buddleSort(int[] arr){
        if(arr == null || arr.length == 0){
            return ;
        }

        int length = arr.length;
        int temp;

        for(int i = 0; i < length; i++){
            for(int j=0; j < length; j++){
                if(arr[i] < arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertionSort(int[] arr){
        if(arr == null || arr.length == 0){
            return ;
        }

        int length = arr.length;
        int temp;

        for(int i = 1; i < length; i++){
            temp = arr[i];
            for(int j=i-1; j >=0 ; j--){
                if(temp < arr[j]){
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectionSort(int[] arr){
        if(arr == null || arr.length == 0){
            return ;
        }

        int length = arr.length;

        int temp = 0;
        for(int i = 0; i < length; i++){

            for(int j = i; j < length;j++){
                if(arr[j] < arr[i]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 7, 3, 2, 1, 5, 7, 8, 3, 2, 23, 2, 2342, 34, 2};
        buddleSort(arr);
        System.out.println(ArrayUtils.toString(arr));

        int[] arr1 = {7, 7, 3, 2, 1, 5, 7, 8, 3, 2, 23, 2, 2342, 34, 2};
        insertionSort(arr1);
        System.out.println(ArrayUtils.toString(arr1));

        int[] arr2 = {7, 7, 3, 2, 1, 5, 7, 8, 3, 2, 23, 2, 2342, 34, 2};
        selectionSort(arr2);
        System.out.println(ArrayUtils.toString(arr2));

    }
}
