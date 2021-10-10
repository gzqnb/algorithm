package com.gzq.algorithm.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//136
public class SingleNumber {
    //方法1:暴力法
    public int singleNumber1(int[] nums) {
        //定义一个列表，保存当前所有出现过一次的元素
        ArrayList<Integer> singNumsList = new ArrayList<>();
        //遍历所有元素
        for (Integer num : nums) {
            if (singNumsList.contains(num)) {
                //如果已经出现过，删除列表的元素
                singNumsList.remove(num);
            } else {
                //没有出现过，直接保存
                singNumsList.add(num);
            }
        }
        return singNumsList.get(0);
    }

    //方法2:保存单独的元素到hashmap
    public int singleNumber2(int[] nums) {
        HashMap<Integer, Integer> singleNumMap = new HashMap<>();
        for (Integer num : nums) {
            if (singleNumMap.get(num) != null) {
                singleNumMap.remove(num);
            } else {
                singleNumMap.put(num, 1);
            }
        }
        return singleNumMap.keySet().iterator().next();
    }

    //方法3:用set去重，a=2*(a+b+c)-(a+b+b+c+c)
    public int singleNumber3(int[] nums) {
        //定义一个hashset进行去重
        HashSet<Integer> hashSet = new HashSet<>();
        int arraySum = 0;
        int setSum = 0;
        //1.遍历数组元素，保存到set，并直接求和
        for (int num : nums) {
            hashSet.add(num);
            arraySum += num;
        }
        //2.集合所有元素求和
        for (int num : hashSet) {
            setSum += num;
        }
        //3.计算结果
        return setSum * 2 - arraySum;
    }

    //方法4:数学方法(做异或)
    public int singleNumber(int[] nums) {
        int result = 0;
        //遍历所有数据，按位做异或
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        SingleNumber singleNumber = new SingleNumber();
        System.out.println(singleNumber.singleNumber(nums));
    }
}
