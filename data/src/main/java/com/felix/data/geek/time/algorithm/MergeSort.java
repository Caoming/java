package com.felix.data.geek.time.algorithm;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int num[] = {1,3,2,1,1,1,5,6,8,9,0};
        new MergeSort().sort(num, 0, num.length-1);
        for (int n : num) {
            System.out.print(n + " ");
        }
    }
    void sort(int num[],int low,int high){
        if(low >= high){
            return;
        }
        int mid = (low+high)/2;
        sort(num,low,mid);
        sort(num,mid+1,high);
        merge(num,low,mid,high);

    }

    public void merge(int[] num, int low, int mid, int high) {
        int leftIndex = low;
        int rightIndex = mid+1;
        int copyIndex = low;
        int[] copyArr = new int[num.length];
        while(leftIndex <= mid && rightIndex <=high){
            if(num[rightIndex] > num[leftIndex]){
                copyArr[copyIndex++] = num[leftIndex++];
            }else {
                copyArr[copyIndex++] = num[rightIndex++];
            }
        }

        while (leftIndex<=mid){
            copyArr[copyIndex++] = num[leftIndex++];
        }

        while (rightIndex<=high){
            copyArr[copyIndex++] = num[rightIndex++];
        }

        while (low <= high )
            num[low] =copyArr[low++];
    }

    public void merge1(int[] data, int left, int center, int right){
        // 临时数组
        int[] tmpArr = new int[data.length];
        // 右数组第一个元素索引
        int mid = center + 1;
        // third 记录临时数组的索引
        int third = left;
        // 缓存左数组第一个元素的索引
        int tmp = left;
        while (left <= center && mid <= right) {
            // 从两个数组中取出最小的放入临时数组
            if (data[left] <= data[mid]) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }
        // 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }
        // 将临时数组中的内容拷贝回原数组中
        // （原left-right范围的内容被复制回原数组）
        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
    }
}
