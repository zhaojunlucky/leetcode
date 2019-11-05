package com.magicworldz.leetcode.targetsum;

import com.magicworldz.leetcode.common.CollectionUtil;
import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TargetSumv2 {
    private Map<String, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
        var app = LeetCode.newInstance(TargetSumv2.class);
        var arr = CollectionUtil.arr(34,21,12,36,16,7,31,7,41,49,7,48,22,19,32,46,19,18,44,34);
        System.out.println(app.findTargetSumWays(arr, 47));
//        var arr = CollectionUtil.arr(1, 1, 1, 1, 1);
//        System.out.println(app.findTargetSumWays(arr, 3));
    }

    @Duration
    public int findTargetSumWays(int[] nums, int S) {
        int sum = Arrays.stream(nums).sum();
        if (S > sum) {
            return 0;
        }
        int len = sum * 2 + 1;
        int[][] dp = new int[nums.length + 1][len];
        dp[0][sum] = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 0; j < len; j++) {
                if (dp[i][j] != 0) {
                    dp[i + 1][j + num] += dp[i][j];
                    dp[i + 1][j - num] += dp[i][j];
                }
            }
        }
        return dp[nums.length][S + sum];
    }
}
