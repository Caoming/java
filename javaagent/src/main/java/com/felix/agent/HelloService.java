package com.felix.agent;

import java.util.HashMap;
import java.util.Map;

public class HelloService {

    public void getName(String s){
        System.out.println("shuchu" + s);
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s == null || "".equals(s)){
            return 0;
        }
        String[] strs = s.split("");

        Map<String,Integer> map = new HashMap<>();
        int maxLength = 0;
        int currentLength = 0;

        for(int i=0; i < strs.length; ){
            String str = strs[i];
            if(map.get(str) == null){
                map.put(str,i);
                currentLength +=1;
                i++;
            }else{
                if(currentLength > maxLength){
                    maxLength = currentLength;
                }
                i = map.get(str)+1;
                map = new HashMap<>();
                currentLength = 0;
            }
        }

        if(currentLength > maxLength){
            maxLength = currentLength;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aab"));
    }
}
