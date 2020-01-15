package com.felix.data.geek.time.algorithm;

import org.junit.Test;

/**
 * 1、如何在 O(n) 的时间复杂度内查找一个无序数组中的第 K 大元素？
 * 2、现在你有 10 个接口访问日志文件，每个日志文件大小约 300MB，
 * 每个文件里的日志都是按照时间戳从小到大排序的。你希望将这 10 个较小的日志文件，
 * 合并为 1 个日志文件，合并之后的日志仍然按照时间戳从小到大排列。
 * 如果处理上述排序任务的机器内存只有 1GB，你有什么好的解决思路，能“快速”地将这 10 个日志文件合并吗？
 */
public class SortTest {

    public static void main(String[] args) {
        int[] arr = new int[]{1,92,12,0,122,3,7,5,45,8,9,34};
        quickSort(arr,5);
        System.out.println(arr[0]);
    }

    public static void quickSort(int[] arr, Integer k){
        if(arr.length == 2){
            System.out.println(arr[k]);
            return;
        }

        int left =0;
        int right = 0;
        int pivot = arr[arr.length-1];
        int[] copyArrLeft = new int[arr.length];
        int[] copyArrRight = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            if(arr[i] <pivot){
                copyArrLeft[left] = arr[i];
                left++;
            }else {
                copyArrRight[right] = arr[i];
                right++;
            }
        }
        if(left>=k){
            int[] targetArr = new int[left];
            for(int i = 0; i < left;i++){
                targetArr[i] = copyArrLeft[i];
            }
            arr = targetArr;
        }else{
            int[] targetArr = new int[right];
            for(int i = 0; i < right;i++){
                targetArr[i] = copyArrRight[i];
            }
            k = k - left-1;
            arr = targetArr;
        }
        quickSort(arr,k);
    }

}
