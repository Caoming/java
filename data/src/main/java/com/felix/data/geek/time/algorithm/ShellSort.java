package com.felix.data.geek.time.algorithm;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {


    public static void main(String[] args) {
        int[] arr = new int[]{1,92,12,0,122,3,7,5,45,8,9,34,1233};

        int[] gaps = new int[]{5,3,1};
        for(int i : gaps){
            sort(i,arr);
        }

        System.out.println(Arrays.toString(arr));


    }

    public static void sort(int gap,int[] arr){
        int temp = 0,j;

        for(int i = gap; i<arr.length;i++){
            temp = arr[i];
            for(j =i-gap;j>=0; j=j-gap){
                if(arr[j] > temp){
                    arr[j+gap] = arr[j];
                }else {
                    break;
                }
            }

            arr[j+gap] = temp;
        }

    }
}
