package com.magicworldz.leetcode.nextpermutation;

import com.magicworldz.leetcode.common.CollectionUtil;

import java.util.Arrays;

public class NextPermutation {

    public static void main(String[] args) {
        var app = new NextPermutation();
        var nums = CollectionUtil.arr(6,2,8,3);
        System.out.println(CollectionUtil.arr2str(nums));
        app.nextPermutation(nums);
        System.out.println(CollectionUtil.arr2str(nums));

        // 1,2,3 → 1,3,2
        // 3,2,1 → 1,2,3
        // 1,1,5 → 1,5,1
        // 1,3,2 → 2,1,3
        // 2,3,1 → 3,1,2
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // find first reverse unsorted index
        int index = nums.length - 2;
        while (index >= 0 && nums[index] >= nums[index + 1]) {
            index--;
        }

        if (index < 0) {
            // all previous number > next number
            // 3, 2, 1
            Arrays.sort(nums);
        } else {
            // nums[index] < nums[index + 1]
            // find a min number from [index + 1, nums.length) where number > nums[index]
            int min = index + 1;
            for (int i = index + 2; i < nums.length; ++i) {
                if (nums[min] > nums[i] && nums[i] > nums[index]) {
                    min = i;
                }
            }
            // then swap nums[index] and nums[min]
            int temp = nums[index];
            nums[index] = nums[min];
            nums[min] = temp;
            // last, sort [index + 1, nums.length)
            Arrays.sort(nums, index + 1, nums.length);
        }
    }
}
