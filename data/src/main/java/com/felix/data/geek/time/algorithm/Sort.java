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

    public static void insertionSort1(int[] arr){
        int temp,j;
        for(int i = 0; i < arr.length;i++){
            temp = arr[i];
            j = i;
            while (j >0 && temp < arr[j-1]){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = temp;
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

        int temp;
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
        insertionSort1(arr1);
        System.out.println(ArrayUtils.toString(arr1));

        int[] arr2 = {7, 7, 3, 2, 1, 5, 7, 8, 3, 2, 23, 2, 2342, 34, 2};
        selectionSort(arr2);
        System.out.println(ArrayUtils.toString(arr2));

        System.out.println(convert("PAYPALISHIRING",3));
        System.out.println(convert("ABC",2));
        System.out.println(convert("LEETCODEISHIRING",3).equals("LCIRETOESIIGEDHN"));
        System.out.println(convert("LEETCODEISHIRING",3).equals("LCIRETOESIIGEDHN"));

    }


    public static String convert(String s, int numRows) {
        if(s == null || "".equals(s) || numRows < 2){
            return s;
        }

        String[] strs= s.split("");
        String targets = "";

        if(numRows == 2){
            for(int i = 0; i < (strs.length % 2  == 0 ? strs.length / 2 : (strs.length /2 +1)); i++){
                targets += strs[2 * i];
            }
            for(int i = 0; i < strs.length / 2; i++){
                targets += strs[2 * i+1];
            }
            return targets;
        }

        int rowSize = strs.length / (numRows + numRows - 2) * (numRows-1);
        int remainderSize = strs.length % (numRows + numRows - 2);
        if(remainderSize <=numRows && remainderSize > 0){
            rowSize++;
        }else if(remainderSize > numRows && remainderSize < numRows + numRows -2){
            rowSize += 2;
        }

        String[][] temps = new String[rowSize][numRows];
        int z = 0;
        for(int i = 0; i < rowSize; i++){
            for(int j = 0; j < numRows; j++){
                if(i % (numRows-1) ==0){
                    if(z > strs.length -1){
                        break;
                    }
                    temps[i][j] = strs[z++];
                }else{
                    if(j == numRows -(i % (numRows-1))-1){
                        if(z > strs.length -1){
                            break;
                        }
                        temps[i][j] = strs[z++];
                    }
                }
            }
        }

        for(int j = 0; j < numRows; j++){
            for(int i = 0; i < rowSize; i++){
                if(temps[i][j] != null){
                    targets += temps[i][j];
                }
            }
        }

        return targets;
    }
}
