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

    /**
     *  字符串中最大的回文数，n^3
     * @param str
     * @return
     */
    public static String getMaxPalindromic(String str){
        if(StringUtils.isBlank(str)){
            return str;
        }

        String maxStr = "";
        String[] strs = str.split("");
        for(int i = 0;i < strs.length; i++){
            for(int j = i; j < strs.length;j++){
                String palindromic = str.substring(i, j + 1);
                if(isPalindromic(palindromic)){
                    maxStr = maxStr.length() < palindromic.length() ? palindromic : maxStr;
                }
            }
        }

        return maxStr;

    }

    public static void repeat(String target){
        if(StringUtils.isBlank(target)){
            return ;
        }

        String[] strs = target.split("");
        String[] copyStrs = new String[strs.length];
        String temps="";
        for(int i = 1,j=0;i < strs.length; i++){
            if(strs[i-1].equals(strs[i])){
                if(temps.length() > 1) {
                    copyStrs[j] = temps;
                    temps ="";
                    j++;
                }
            }else {
                if(strs[i-1].equals(i< 2 ? strs[i-1] + "s":strs[i-2])){
                    temps = strs[i];
                    continue;
                }
                if((i+1) < (strs.length-1) && strs[i].equals(strs[i+1])){
                    continue;
                }
                temps += strs[i];
            }
            if(i == strs.length -1){
                copyStrs[j] = temps;
            }
        }

        for (String s: copyStrs){
            if(StringUtils.isNotBlank(s)){
                System.out.println("...."+s);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(isPalindromic(""));
        System.out.println(isPalindromic("a"));
        System.out.println(isPalindromic("aba"));
        System.out.println(isPalindromic("abab"));
        System.out.println(isPalindromic("abba"));
        System.out.println(isPalindromic("ababa"));
        System.out.println(isPalindromic("ababaa"));
        System.out.println(getMaxPalindromic("absbdsasduewsdasd"));
        System.out.println(getMaxPalindromic("bb23ksdlakd"));
//        System.out.println(getMaxPalindromic("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
        repeat("aabbdeseesdffdsfssds");
    }



}
