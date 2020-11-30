package com.sp.spproj.algrithm;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author: Jianjun
 * @date: 2020-10-29 17:29
 * @version:
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(new String[]{"ab", "a"}));
    }

    public static String lengthOfLongestSubstring(String[] strs) {

        if (strs.length == 0 || strs[0].equals("")){
            return "";
        }

        if (strs.length == 1){
            return strs[0];
        }

        String minStr = strs[0];
        int minLength = strs[0].length();
        for (int i=1;i<strs.length;i++){
            if (minLength > strs[i].length()){
                minLength = strs[i].length();
                minStr = strs[i];
            }
        }

        int j = 1;
        String pre;
        int maxSize;
        while (true) {
            pre = minStr.substring(0,j);
            maxSize = pre.length();

            boolean isBreak =  false;
            for (int index = 0; index < strs.length; index++) {
                if (!strs[index].startsWith(pre)) {
                    maxSize--;
                    isBreak = true;
                    break;
                }
                if (maxSize > pre.length()){
                    maxSize = pre.length();
                }
            }
            if (minLength == j){
                break;
            }

            if (isBreak){
                break;
            }
            j++;
        }

        if (pre.length() == 1){
            return pre;
        }
        return pre.substring(0, pre.length()-1);

    }
}
