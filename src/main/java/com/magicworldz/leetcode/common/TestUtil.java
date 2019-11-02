package com.magicworldz.leetcode.common;

import java.util.List;

public class TestUtil {
    private TestUtil() {

    }

    public static void println(String ... strs) {
        if (strs != null) {
            System.out.println(String.join(" ", strs));
        }
    }

    public static <T> void println(List<List<T>> list) {
        list.forEach(e-> System.out.println(list2str(e)));
    }

    public static <T> String list2str(List<T> list) {
        return list.stream().map(e->e.toString()).reduce((x, y)-> String.format("%s, %s", x, y)).orElseGet(()-> "");
    }
}
