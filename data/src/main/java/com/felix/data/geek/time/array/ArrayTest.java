package com.felix.data.geek.time.array;

import java.util.Arrays;

/**
 * 数组的练习
 */
public class ArrayTest {

    private static Integer[] ARRAY_TEST_INT = new Integer[]{1,2,3,4,5,6,7,8,9,0};
    private static String[] ARRAY_TEST_STR = new String[]{"a","b","c","d","e","f","g","h","i","m"};

    // 扩容
    private final static int capacity = 16;


    public static void main(String[] args) {
        int i = 0;
        int[] arr = new int[3];
        for(;i < 2;i++){
            arr[i] = 0;
            System.out.println("hello word");
        }
        System.out.println(arr.length + "...."+arr[2]);

        String[] arrStr = new String[3];
        for(int j = 0; j < 2; j++){
            arrStr[j] = "s";
        }
        System.out.println(arrStr.length + "...."+arrStr[2]);

        System.out.println(16 >> 1);

        String[] strings = add(arrStr, "222");
        for(String s: strings){
            System.out.println(s);
        }

        System.out.println(isExist(arrStr,"s"));

        remove(arrStr,"222");
        for (String s : arrStr) {
            System.out.println(s);
        }
    }

    /**
     * 添加到数组
     * @param arrT
     * @param t
     * @param <T>
     */
    public static <T> T[] add(T[] arrT, T t){
        if(arrT == null || arrT.length == 0){
            System.out.println("数组不能为空或null");
        }
        int length = arrT.length;

        if(arrT[length -1] != null){// 数组需要扩容
            T[] ts = Arrays.copyOf(arrT, length + 1);
            ts[length] = t;
            return ts;
        }else {
            for(int i = length-2;i >= 0; i--){
                if(arrT[i] != null){
                    arrT[i+1] = t;
                    break;
                }
            }
        }
        return arrT;
    }

    /**
     * 数组是否存在对应的值
     * @param arrT
     * @param t
     * @param <T>
     * @return
     */
    public static <T> boolean isExist(T[] arrT, T t){
        if(arrT == null || arrT.length == 0){
            System.out.println("数组不能为空或null");
            return false;
        }
        for(T t1 : arrT){
            if(t instanceof Number){
                if(t1 == t){
                    return true;
                }
            }

            if(t1.equals(t)){
                return true;
            }
        }

        return false;
    }

    /**
     * 删除对应的值
     * @param arrT
     * @param t，多个值的时候考虑其算法
     * @param <T>
     * @return
     */
    public static <T> boolean remove(T[] arrT, T t){
        if(arrT == null || arrT.length == 0){
            System.out.println("数组不能为空或null");
            return false;
        }

        int index = 0;
        for(int i = 0; i < arrT.length; i++){

            if(arrT[i].equals(t)){// 找到相同的值
                index = i;
                if(i+1 < arrT.length){// 值进行替换
                    arrT[i] = arrT[i+1];
                }else {
                    arrT[i] = null;
                }
            }
            if(i > index){
                if(i+1 < arrT.length){// 之后的值进行向前进行处理
                    arrT[i] = arrT[i+1];
                }else if(i == arrT.length -1){
                    arrT[i] = null;
                }
            }
        }

        return false;
    }
}
