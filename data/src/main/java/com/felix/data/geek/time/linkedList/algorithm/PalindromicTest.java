package com.felix.data.geek.time.linkedList.algorithm;

import org.apache.commons.lang3.StringUtils;

/**
 * 回文算法的实现
 */
public class PalindromicTest {

    /**
     * 判断是否是回文数
     * @param str
     * @return
     */
    public static boolean isPalindromic(String str){
        if(StringUtils.isBlank(str)){
            return false;
        }

        String[] strings = str.split("");

        int length = strings.length;
        if(length == 1){
            return true;
        }

        int index = length / 2;
        for(int i = 0; i < index; i++){
            if(!strings[i].equals(strings[length-1-i])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromic(""));
        System.out.println(isPalindromic("a"));
        System.out.println(isPalindromic("aba"));
        System.out.println(isPalindromic("abab"));
        System.out.println(isPalindromic("abba"));
        System.out.println(isPalindromic("ababa"));
        System.out.println(isPalindromic("ababaa"));
    }
}
