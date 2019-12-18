package com.felix.data.geek.time.algorithm;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1,92,12,0,122,3,7,5,45,8,9,34};
        quickSort(arr,0,arr.length-1);
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
            quickSort(arr,mid+1,pivot);
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
}
