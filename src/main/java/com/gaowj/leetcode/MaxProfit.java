package com.gaowj.leetcode;

/**
 * created by gaowj.
 * created on 2021-03-29.
 * function: 121. 买卖股票的最佳时机(DP)
 * origin -> https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-by-leetcode-/
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/gu-piao-wen-ti-python3-c-by-z1m/
 */
public class MaxProfit {
    //暴力解题
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int nowPri = prices[j] - prices[i];
                if (nowPri > maxprofit)
                    maxprofit = nowPri;
            }
        }
        return maxprofit;
    }

    //一次遍历
    public int maxProfit1(int[] prices) {
        if (prices.length == 0)
            return 0;
        int minPri = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPri)
                minPri = prices[i];
            else if (prices[i] - minPri > maxprofit)
                maxprofit = prices[i] - minPri;
        }
        return maxprofit;
    }

    //动态规划
    public int maxProfit2(int[] prices) {
        if (prices.length == 0)
            return 0;
        int[] dp = new int[prices.length];
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(prices[i], minPrice);
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }
        return dp[prices.length - 1];
    }
}
