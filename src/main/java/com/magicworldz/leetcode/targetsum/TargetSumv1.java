package com.magicworldz.leetcode.targetsum;

import com.magicworldz.leetcode.common.CollectionUtil;
import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.DurationProxy;
import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class TargetSumv1 {
    private Map<String, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
        var app = LeetCode.newInstance(TargetSumv1.class);
        var arr = CollectionUtil.arr(34,21,12,36,16,7,31,7,41,49,7,48,22,19,32,46,19,18,44,34);
        System.out.println(app.findTargetSumWays(arr, 47));
    }

    @Duration
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

        String key = String.format("%d-%d", index, curSum);
        if (cache.containsKey(key)) {
            //System.out.println("cached key:" + key);
            return cache.get(key);
        }
        int ans = 0;
        symbols[index] = '-';
        ans += targetSum(nums, S, curSum - nums[index], index + 1, symbols);
        symbols[index] = '+';
        ans += targetSum(nums, S, curSum + nums[index], index + 1, symbols);
        cache.put(key, ans);
        return ans;
    }
}
