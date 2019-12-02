package com.felix.data.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 链接：https://leetcode-cn.com/problems/two-sum
 *
 * 示例：
 *  给定 nums = [2, 7, 11, 15], target = 9
 *
 *  因为 nums[0] + nums[1] = 2 + 7 = 9
 *  所以返回 [0, 1]
 */
public class Number1 {

    public static void main(String[] args) {
//        int[] twoSum = twoSum(new int[]{2, 7, 11, 3, 23}, 9);
//        int[] twoSum = twoSum(new int[]{2, 7, 11, 3, 23}, 5);
        int[] twoSum = twoSum(new int[]{2, 7}, 9);
        System.out.println(twoSum[0]+","+twoSum[1]);
    }

    /**
     * 解法1
     * 复杂度为O(n)
     * @param arr
     * @param target
     * @return
     */
    public static int[] twoSum(int[] arr,int target){
        if(arr == null || arr.length == 0 || arr.length == 1){
            throw new RuntimeException("传的参数存在问题");
        }

        int[] indexArray = new int[2];
        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < arr.length; i++){
            if(map.get(target-arr[i]) == null){
                map.put(arr[i],i);
            }else{
                indexArray[0] = map.get(target-arr[i]);
                indexArray[1] = i;
                return indexArray;
            }
        }

        return indexArray;
    }

}
