package com.felix.test;

import org.junit.Test;

/**
 * 选中几个值，判断是否等于指定的值
 * @Author caoming
 * @Date: 2020/10/4 08:53
 */
public class DynamicPlanningTest2 {
    @Test
    public void testArr(){
        int[] arr = new int[]{3,34,4,12,5,2};
        System.out.println(recSubSet(arr,arr.length-1, 9));
        System.out.println(recSubSet(arr,arr.length-1, 10));
        System.out.println(recSubSet(arr,arr.length-1, 11));
        System.out.println(recSubSet(arr,arr.length-1, 12));
        System.out.println(recSubSet(arr,arr.length-1, 13));
    }

    public boolean recSubSet(int[] arrs, int i,int target){
        if(target == 0){
            return true;
        }else if( i == 1){
            return arrs[i] == target;
        }else if(arrs[i] > target) {
            return recSubSet(arrs, i -1, target);
        }else{
         boolean A =  recSubSet(arrs,i - 1, target - arrs[i]) ;
         boolean B = recSubSet(arrs, i - 1, target);
         return A || B;
        }
    }
}
