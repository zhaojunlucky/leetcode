package com.magicworldz.leetcode.steelstripcutting;

import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SteelStripCutting {
    public static void main(String[] args) {
        var app = LeetCode.newInstance(SteelStripCutting.class);
        int[] lens = new int[]   {1, 2, 3, 4,  5,  6,  7,  8,  9, 10};
        int[] prices = new int[] {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        List<Integer> cuttings = new ArrayList<>();
        System.out.println(app.cutSteelStrip(7, lens, prices, cuttings));
    }

    @Duration
    public int cutSteelStrip(int n, int[] lens, int[] prices, List<Integer> cuttings) {
        // p(n) = max{p(n), p(1)+p(n-1), p(2)+p(n-2),..., p(n-1)+p1}
        Map<Integer, Integer> lenPriceMap = new HashMap<>();
        for (int i = 0; i < lens.length; ++i) {
            lenPriceMap.put(lens[i], prices[i]);
        }
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            dp[i] = getLenPrice(lenPriceMap, i);
            for (int j = 1; j <= i; ++j) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i-j]);
            }
        }
        return dp[n];
    }

    private int getLenPrice(Map<Integer, Integer> lenPriceMap, int len) {
        if (lenPriceMap.containsKey(len)) {
            return lenPriceMap.get(len);
        }
        return 0;
    }
}
