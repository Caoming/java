package com.felix.data.geek.time.algorithm;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1,92,12,0,122,3,7,5,45,8,9,34,1233};
        quickSort1(arr,0,arr.length-1);
        for(int i : arr){
            System.out.println(i);
        }
    }

    /**
     * 排序的数据
     * @param arr
     * @param low
     * @param pivot
     * @return
     */
    public static void quickSort(int[] arr,int low,int pivot){
        if(pivot > low && low >= 0){
            int mid = partition(arr,low,pivot);
            quickSort(arr,low,mid-1);
            quickSort(arr,mid,pivot);
        }
    }

    /**
     * 分区
     * @param arr
     * @param low
     * @param pivot
     * @return
     */
    public static int partition(int[] arr,int low,int pivot){
        int left = low;
        int right = pivot;
        // 每次递归新建一个空间
        int[] copyArr = new int[arr.length];
        int mid = arr[pivot];
        for(int i = low; i <=pivot; i++){
            if(arr[i] < mid){
                copyArr[left] = arr[i];
                left++;
            }else {
                copyArr[right] = arr[i];
                right--;
            }
        }

        for(int i = low; i <= pivot; i++){
            arr[i] = copyArr[i];
        }
        return  left;
    }


    public static void quickSort1(int[] arr,int start,int end){
        if(end -start >0) {
            int mid = partition1(arr, start, end);
            quickSort1(arr,start,mid-1);
            quickSort1(arr,mid+1,end);
        }
    }

    public static int partition1(int[] arr,int start,int end){
        int length = end - start + 1;
        int left = start;
        int right = end;
        int[] copyArr = new int[arr.length];
        for(int i = start; i< start + length; i++){
            if(arr[end]>arr[i]){
                copyArr[left] = arr[i];
                left++;
            }else {
                copyArr[right] = arr[i];
                right--;
            }
        }

        for(int i = start; i < start + length; i++){
            arr[i] = copyArr[i];
        }
        return left;
    }
}
