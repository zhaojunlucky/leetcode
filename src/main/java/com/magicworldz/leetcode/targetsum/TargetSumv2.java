package com.magicworldz.leetcode.targetsum;

import com.magicworldz.leetcode.common.CollectionUtil;
import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class TargetSumv2 {
    private Map<String, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
        var app = LeetCode.newInstance(TargetSumv2.class);
        var arr = CollectionUtil.arr(34,21,12,36,16,7,31,7,41,49,7,48,22,19,32,46,19,18,44,34);
        System.out.println(app.findTargetSumWays(arr, 47));
    }

    @Duration
    public int findTargetSumWays(int[] nums, int S) {
        return targetSum(nums, S, 0, 0);
    }

    public int targetSum(int[] nums, int S, int curSum, int index) {

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
        ans += targetSum(nums, S, curSum - nums[index], index + 1);
        ans += targetSum(nums, S, curSum + nums[index], index + 1);
        cache.put(key, ans);
        return ans;
    }
}
