package com.gzq.algorithm.arrays;

import java.util.HashMap;

public class TwoSum {
    //方法一：暴力法，穷举
    public int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        //找不到抛出异常
        throw new IllegalArgumentException("no solution");
    }

    //方法2:hashmap
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        throw new IllegalArgumentException("no solution");
    }

    //方法3:hashmap+1次for循环
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no solution");
    }

    public static void main(String[] args) {
        int[] input = {3, 3};
        int target = 6;
        TwoSum twoSum = new TwoSum();
        int[] ints = twoSum.twoSum(input, target);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
