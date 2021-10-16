package com.gzq.algorithm.dynamic_programming;

//121
public class BestTimeToBuyAndSellStock {
    //方法1:暴力法
    public int maxProfit1(int[] prices) {
        int maxProfit = 0;
        //遍历所有可能的买入卖出的情况
        for (int i = 0; i < prices.length - 1; i++) {//最后一天买没比要，卖不出去
            for (int j = i; j < prices.length; j++) {
                int currProfit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, currProfit);
            }
        }
        return maxProfit;
    }

    //方法2:动态规划
    public int maxProfit(int[] prices) {
        //定义状态:保存到目前位置的最小价格和最大利润
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        //遍历数组元素，以当前价格作为卖出点进行比较
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit,prices[i]-minPrice);
        }
        return maxProfit;
    }
}
