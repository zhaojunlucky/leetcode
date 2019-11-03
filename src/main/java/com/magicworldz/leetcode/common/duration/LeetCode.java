package com.magicworldz.leetcode.common.duration;

import net.sf.cglib.proxy.Enhancer;

public class LeetCode {
    private static final LeetCode INS = new LeetCode();


    public static <T> T newInstance(Class<T> cls) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(cls);
        enhancer.setCallback(new DurationMethodInterceptor());
        return (T)enhancer.create();
    }
}
