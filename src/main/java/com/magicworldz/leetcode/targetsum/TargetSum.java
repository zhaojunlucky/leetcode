package com.magicworldz.leetcode.targetsum;

import com.magicworldz.leetcode.common.CollectionUtil;

public class TargetSum {
    public static void main(String[] args) {
        var app = new TargetSum();
        System.out.println(app.findTargetSumWays(CollectionUtil.arr(1, 1, 1, 1, 1), 3));
    }

    public int findTargetSumWays(int[] nums, int S) {
        char[] symbols = new char[nums.length];
        return targetSum(nums, S, 0, 0, symbols);
    }

    public int targetSum(int[] nums, int S, int curSum, int index, char[] symbols) {

        if (index == nums.length && curSum == S) {
            return 1;
        } else if (index == nums.length) {
            return 0;
        }


        int ans = 0;
        symbols[index] = '-';
        ans += targetSum(nums, S, curSum - nums[index], index + 1, symbols);
        symbols[index] = '+';
        ans += targetSum(nums, S, curSum + nums[index], index + 1, symbols);

        return ans;
    }
}
