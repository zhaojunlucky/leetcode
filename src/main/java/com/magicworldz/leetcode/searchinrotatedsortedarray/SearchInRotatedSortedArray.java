package com.magicworldz.leetcode.searchinrotatedsortedarray;

import com.magicworldz.leetcode.common.CollectionUtil;


public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = CollectionUtil.arr(1, 3);
        //int[] nums = CollectionUtil.arr(4,5,6,7,0,1,2);
        var app = new SearchInRotatedSortedArray();

        for (int n : nums) {
            System.out.println(String.format("%d = %d", n, app.search(nums, n)));
        }

        System.out.println(String.format("%d = %d", -1, app.search(nums, -1)));
    }

    public int search(int[] nums, int target) {

        int l = 0;
        int h = nums.length - 1;

        // left part > right part
        while (l <= h) {
            int m = (l + h) >>> 1;

            if (target == nums[m]) {
                return m;
            }

            // [l, m] are ordered
            if (nums[m] > nums[l] ) {
                if (target < nums[m] && target >= nums[l]) {
                    // find in [l, m-1]
                    h = m - 1;
                } else {
                    // find in [m + 1, h]
                    l = m + 1;
                }
            } else if (nums[m] < nums[h]) {
                // [m, h] are ordered

                if (target > nums[m] && target <= nums[h]) {
                    // find in [m + 1, h]
                    l = m + 1;
                } else {
                    // find in [l, m - 1]
                    h = m - 1;
                }
            } else {
                if (target < nums[l]) {
                    l = m + 1;
                } else {
                    h = m - 1;
                }
            }


        }

        return -1;
    }
}
