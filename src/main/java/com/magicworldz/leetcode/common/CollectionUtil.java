package com.magicworldz.leetcode.common;

import java.util.Arrays;
import java.util.Objects;

public class CollectionUtil {
    private CollectionUtil() {

    }

    public static int[] arr(int ...nums) {
        Objects.requireNonNull(nums);
        return nums;
    }

    public static String arr2str(int[] nums) {
        return Arrays.stream(nums).mapToObj(String::valueOf)
                .reduce((l, r)-> String.format("%s, %s", l, r)).get();
    }

    public static long[] arr(long ...nums) {
        Objects.requireNonNull(nums);
        return nums;
    }

    public static double[] arr(double ...nums) {
        Objects.requireNonNull(nums);
        return nums;
    }

    public static byte[] arr(byte ...nums) {
        Objects.requireNonNull(nums);
        return nums;
    }

    public static float[] arr(float ...nums) {
        Objects.requireNonNull(nums);
        return nums;
    }

    public static char[] arr(char ...nums) {
        Objects.requireNonNull(nums);
        return nums;
    }

    public static short[] arr(short ...nums) {
        Objects.requireNonNull(nums);
        return nums;
    }
}
