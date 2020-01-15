package com.felix.data.geek.time.algorithm;

import org.junit.Test;

/**
 * 二分查找法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,11,12,15,23,43,46,67};
        System.out.println(isExist(arr,2));


        System.out.println(digui(arr,0,arr.length-1,67));
    }

    /**
     * 循环的二分查找
     * @param arr
     * @param target
     * @return
     */
    public static int isExist(int[] arr , int target){
        int length = arr.length;
        int mid = length >> 1;
        int start = 0;
        int end = length;
        while (true){
            if((end -start) <= 1){
                return -1;
            }

            if(arr[mid] > target ){
                end = mid;
                mid = ((end - start + 1) >> 1) + start;
            }else if(arr[mid] == target){
                return mid;
            }else{
                start = mid;
                mid = ((end - start + 1) >> 1) + start;
            }
        }

    }

    @Test
    public void maual(){

        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,11,12,15,23,43,46,67};
        int target = 1;
        int start = 0;
        int end = arr.length;
        while (start < end){
            int mid = (start + end) >> 1;
            if(arr[mid] > target){
                end = mid;
            }else if(arr[mid] == target){
                System.out.println(arr[mid] +"index:" +mid);
                return;
            }else {
                start = mid;
            }
        }

    }

    /**
     * 递归二分法
     * @param arr
     * @param start
     * @param end
     * @param target
     * @return
     */
    public static int digui(int[] arr,int start,int end,int target){
        int index = -1;
        if(end -start >1){
            int mid = ((end -start) >> 1) + start;
            if(arr[mid] > target){
                end = mid;
                index = digui(arr,start,end,target);
            }else if(arr[mid] == target){
                return mid;
            }else {
                start = mid;
                index = digui(arr,start,end,target);
            }
        }

        return index;
    }



}
