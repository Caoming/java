package com.felix.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 间隔相加最大的值
 * @Author caoming
 * @Date: 2020/10/4 08:53
 */
public class DynamicPlanningTest1 {
    @Test
    public void testArr(){
        int[] arr = new int[]{1,2,4,1,7,8,3};
        System.out.println(recOpt(arr,arr.length-1));
    }

    @Test
    public void testArr1(){
        int[] arr = new int[]{1,2,4,1,7,8,3};
        int[] copyArr = new int[arr.length];
        copyArr[0] = arr[0];
        copyArr[1] = Math.max(arr[0], arr[1]);
        for(int i = 2; i < arr.length; i++ ){
            int A = copyArr[i - 2] + arr[i];
            int B = copyArr[i-1];
            copyArr[i] = Math.max(A, B);
        }
        System.out.println(copyArr[arr.length -1 ]);
    }

    public int recOpt(int[] arrs, int i){
        if(i == 0){
            return arrs[0];
        }else if( i == 1){
            return Math.max(arrs[0],arrs[1]);
        }else{
         int A =  recOpt(arrs,i - 2) + arrs[i];
         int B = recOpt(arrs, i - 1);
         return Math.max(A,B);
        }
    }
}
