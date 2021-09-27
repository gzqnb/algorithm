package com.gzq.algorithm.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    //方法1:暴力 存在问题 没有去重
    public List<List<Integer>> threeSum1(int nums[]) {
        int n = nums.length;
        ArrayList<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0)
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }
        return res;
    }

    //方法2:双指针
    public List<List<Integer>> threeSum(int nums[]) {
        int n = nums.length;
        ArrayList<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        //遍历每一个元素作为当前三元组中最小的那个(最矮个做核心)
        for (int i = 0; i < n; i++) {
            //如果当前数已经大于0，直接退出循环
            if (nums[i] > 0) break;
            //如果当前数据已经出现过,直接跳过
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            //以当前数做最小数，定义左右指针
            int lp = i + 1;
            int rp = n - 1;
            //只要左右指针不重叠
            while (lp < rp) {
                int sum = nums[i] + nums[lp] + nums[rp];
                //判断sum，与0做大小对比
                if (sum == 0) {
                    //等于0，就是找到了一组解
                    res.add(Arrays.asList(nums[i], nums[lp], nums[rp]));
                    lp++;
                    rp--;
                    //如果移动之后的元素相同，直接跳过
                    while (lp < rp && nums[lp] == nums[lp - 1]) lp++;
                    while (lp < rp && nums[rp] == nums[rp + 1]) rp--;
                } else if (sum < 0) lp++;
                else rp--;
            }
        }
        return res;

    }

    public static void main(String[] args) {
        int[] input = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(input);
        System.out.println(lists);
    }
}
