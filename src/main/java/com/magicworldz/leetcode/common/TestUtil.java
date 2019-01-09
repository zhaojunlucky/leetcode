package com.magicworldz.leetcode.common;

public class TestUtil {
    private TestUtil() {

    }

    public static void println(String ... strs) {
        if (strs != null) {
            System.out.println(String.join(" ", strs));
        }
    }
}
