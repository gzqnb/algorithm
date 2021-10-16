package com.gzq.algorithm.dynamic_programming;

public class KnapsackProblem {
    //动态规划实现
    public int maxValue1(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        //定义状态
        int[][] dp = new int[n + 1][capacity + 1];
        //遍历所有子问题，以此计算状态
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                //判断当前的背包容量j是否能放下物品i
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][capacity];
    }

    //动态规划实现，空间优化
    public int maxValue(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        //定义状态,只保存一行数据
        int[] dp = new int[capacity + 1];
        //遍历所有子问题，以此计算状态
        for (int i = 1; i <= n; i++) {
            for (int j = capacity; j > 0; j--) {//涉及到更新数组问题，如果从0开始，改变了前一行对应的数据影响判断，所以倒着来
                //判断当前的背包容量j是否能放下物品i
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        int W = 150;
        int[] w = {35, 30, 60, 50, 40, 10, 25};
        int[] v = {10, 40, 30, 50, 35, 40, 30};
        KnapsackProblem knapsackProblem = new KnapsackProblem();
        System.out.println(knapsackProblem.maxValue(W, w, v));
    }
}
