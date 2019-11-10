package com.magicworldz.leetcode.longestcommonsubsequence;

import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

public class LCS {
    public static void main(String[] args) {
        var app = LeetCode.newInstance(LCS.class);
        System.out.println(app.longestCommonSubsequence("a", "b"));
    }

    @Duration
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
