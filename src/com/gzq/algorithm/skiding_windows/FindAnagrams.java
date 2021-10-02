package com.gzq.algorithm.skiding_windows;

import java.util.ArrayList;
import java.util.List;

public class FindAnagrams {
    //方法一：暴力法，枚举所有的长度为p.length()的子串
    public List<Integer> findAnagrams1(String s, String p) {
        //定义一个结果列表
        ArrayList<Integer> res = new ArrayList<>();
        //1.统计p中所有字符频次
        int[] pCharCounts = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pCharCounts[p.charAt(i) - 'a']++;
        }
        //2.遍历s，以每一个字符作起始，考察长度为p.length()的子串
        for (int i = 0; i <= s.length() - p.length(); i++) {
            //3.判断当前子串是否为p的字母异位词
            boolean isMatched = true;
            //定义一个数组，统计子串中所有字符频次
            int[] subStrCounts = new int[26];
            for (int j = i; j < i + p.length(); j++) {
                subStrCounts[s.charAt(j) - 'a']++;
                //判断当前字符频次如果超过了p中的频次，就一定不符合要求
                if (subStrCounts[s.charAt(j) - 'a'] > pCharCounts[s.charAt(j) - 'a']) {
                    isMatched = false;
                    break;
                }
            }
            if (isMatched) {
                res.add(i);
            }
        }
        return res;
    }

    //滑动窗口,分别移动起始和结束位置
    public List<Integer> findAnagrams(String s, String p) {
        //定义一个结果列表
        ArrayList<Integer> res = new ArrayList<>();
        //1.统计p中所有字符频次
        int[] pCharCounts = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pCharCounts[p.charAt(i) - 'a']++;
        }
        //统计子串中所有字符出现频次的数组
        int[] subStrCounts = new int[26];
        //定义双指针,指向窗口的起始和结束位置
        int start = 0, end = 1;
        //2.移动指针，总是截取字符出现频次全部小于等于p中字符频次的子串
        while (end <= s.length()) {
            //当前新增字符
            char newChar = s.charAt(end - 1);
            subStrCounts[newChar - 'a']++;

            //3.判断当前子串是否符合要求
            //如果新增字符导致子串中频次超出了p中的频次，那么移动start消除新增字符的影响
            while (subStrCounts[newChar - 'a'] > pCharCounts[newChar - 'a'] && start < end) {
                char removeChar = s.charAt(start);
                subStrCounts[removeChar - 'a']--;
                start++;
            }
            //如果当前子串长度等于p的长度，那么就是一个字母异位词
            if (end - start == p.length()) {
                res.add(start);
            }
            end++;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        FindAnagrams findAnagrams = new FindAnagrams();
        System.out.println(findAnagrams.findAnagrams(s, p));
    }
}
