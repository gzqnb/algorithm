package com.gzq.algorithm.binary_search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//287
public class FindDuplicate {
    //使用hashmap保存每个数出现的次数
    public int findDuplicate1(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        //遍历所有元素
        for (Integer num : nums) {
            if (countMap.containsKey(num)) {//如果出现过，number就是重复数
                return num;
            } else countMap.put(num, 1);
        }
        return -1;
    }

    //hashset
    public int findDuplicate2(int[] nums) {
        HashSet<Integer> countSet = new HashSet<>();
        //遍历所有元素,添加到set
        for (Integer num : nums) {
            if (countSet.contains(num)) {//如果出现过，number就是重复数
                return num;
            } else countSet.add(num);
        }
        return -1;
    }

    //先排序，然后再找相邻的相同元素,空间复杂度降低，但是时间复杂度增加
    public int findDuplicate3(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    //二分查找，查找的不是原始数组，查找1~N的自然数序列，寻找target
    public int findDuplicate4(int[] nums) {
        //定义左右指针
        int left = 1;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            //对当前mid计算count值
            int count = 0;
            for (int num : nums) {
                if (num <= mid) count++;
            }
            //判断count和mid本身的大小关系
            if (count <= mid) {
                left = mid + 1;//count小于等于mid，说明mid比target小，左指针右移
            } else right = mid;
            if (left == right) {
                return left;
            }
        }
        return -1;
    }

    //O(n)时间 O(1)空间,快慢指针
    public int findDuplicate(int[] nums) {
        //定义快慢指针
        int fast = 0,slow = 0;
        //1.寻找环内相遇点
        do{//先do是因为开始fast和slow相等，所以让他先进行一次
            //快指针一次走两步，慢指针一次走一步
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (fast!=slow);
        //循环结束，slow和fast相等，都是相遇点
        //2.寻找环的入口点
        //另外定义两个指针，固定间距
        int before = 0,after = slow;
        while (before!=after){
            before=nums[before];
            after = nums[after];
        }
        //循环结束，相遇点就是环的入口点，对应的索引也就是重复元素
        return before;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 4, 5};
        FindDuplicate findDuplicate = new FindDuplicate();
        int duplicate = findDuplicate.findDuplicate(nums);
        System.out.println(duplicate);
    }
}
