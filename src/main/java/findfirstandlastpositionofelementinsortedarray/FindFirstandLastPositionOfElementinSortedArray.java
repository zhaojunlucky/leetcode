package findfirstandlastpositionofelementinsortedarray;

import com.magicworldz.leetcode.common.CollectionUtil;
import com.magicworldz.leetcode.common.TestUtil;

public class FindFirstandLastPositionOfElementinSortedArray {

    public static void main(String[] args) {
        var app = new FindFirstandLastPositionOfElementinSortedArray();

        TestUtil.println(CollectionUtil.arr2str(app.searchRange(CollectionUtil.arr(5,7,7,8,8,10), 6)));
    }

    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        int[] notFound = new int[] {-1, -1};
        while (l <= h) {
            int m = (l + h) >>> 1;
            if (nums[m] == target) {
                int s = m;
                int e = m;
                while (s > 0 && nums[s - 1] == target) {
                    --s;
                }

                while (e < h && nums[e + 1] == target) {
                    ++e;
                }
                return new int[] {s, e};
            } else if (target > nums[m]) {
                l = m + 1;
            } else {
                h = m - 1;
            }

        }
        return notFound;
    }
}