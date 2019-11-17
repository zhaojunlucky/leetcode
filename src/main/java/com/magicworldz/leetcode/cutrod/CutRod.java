package com.magicworldz.leetcode.cutrod;

import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.*;

public class CutRod {
    public static void main(String[] args) {
        var app = LeetCode.newInstance(CutRod.class);
        int[] lens = new int[]   {1, 2, 3, 4,  5,  6,  7,  8,  9, 10};
        int[] prices = new int[] {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        List<Integer> cuttings = new ArrayList<>();
        System.out.println("Max price: " + app.cutRod(7, lens, prices, cuttings));
        Collections.sort(cuttings);
        System.out.println("cuttings: " + cuttings.toString());
    }

    @Duration
    public int cutRod(int n, int[] lens, int[] prices, List<Integer> cuttings) {
        // p(n) = max{p(n), p(1)+p(n-1), p(2)+p(n-2),..., p(n-1)+p1}
        Map<Integer, Integer> lenPriceMap = new HashMap<>();
        for (int i = 0; i < lens.length; ++i) {
            lenPriceMap.put(lens[i], prices[i]);
        }
        int[] dp = new int[n + 1];
        int[][] b = new int[2][n + 1];
        for (int i = 1; i <= n; ++i) {
            dp[i] = getLenPrice(lenPriceMap, i);
            b[0][i] = b[1][i] = i;
            for (int j = 1; j <= i; ++j) {
                int p = dp[j] + dp[i-j];
                if (p > dp[i]) {
                    dp[i] = p;
                    b[0][i] = i - j;
                    b[1][i] = j;
                }
            }
        }

        calcCuts(n, b, cuttings);
        return dp[n];
    }

    private void calcCuts(int n, int[][] b, List<Integer> cuttings) {
        int i;
        int j;
        int k;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        while (!queue.isEmpty() && n > 0) {
            k = queue.poll();
            i = b[0][k];
            j = b[1][k];
            if (i == j) {
                cuttings.add(i);
                queue.add(i);
                n -= i;
            } else {
                cuttings.add(i);
                cuttings.add(j);
                queue.add(i);
                queue.add(j);
                n -= i;
                n -= j;
            }
        }
    }

    private int getLenPrice(Map<Integer, Integer> lenPriceMap, int len) {
        if (lenPriceMap.containsKey(len)) {
            return lenPriceMap.get(len);
        }
        return 0;
    }
}
