package com.magicworldz.leetcode.permutations;

import com.magicworldz.leetcode.common.CollectionUtil;
import com.magicworldz.leetcode.common.TestUtil;
import com.magicworldz.leetcode.common.duration.Duration;
import com.magicworldz.leetcode.common.duration.LeetCode;

import java.util.*;

public class Permutations {
    public static void main(String[] args) {
        var app = LeetCode.newInstance(Permutations.class);
        TestUtil.println(app.permute(CollectionUtil.arr(1, 2, 3)));
    }

    @Duration
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Stack<Integer> cur = new Stack<Integer>();
        backtrace(nums, ans, cur, visited);
        return ans;
    }

    private void backtrace(int[] nums, List<List<Integer>> ans, Stack<Integer> cur, boolean[] visited) {
        if (cur.size() == nums.length) {
            ans.add(new ArrayList<>(cur));
        }
        for (int i = 0; i < nums.length; ++i) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            cur.push(nums[i]);
            backtrace(nums, ans, cur, visited);
            cur.pop();
            visited[i] = false;
        }
    }
}
