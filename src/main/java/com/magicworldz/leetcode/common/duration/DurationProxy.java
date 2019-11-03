package com.magicworldz.leetcode.common.duration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DurationProxy implements InvocationHandler {
    private Object target;

    private DurationProxy(Object target) {
        this.target = target;
    }

    public static <T, R extends T> T newDurationProxy(R target) {
        var proxy = new DurationProxy(target);
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Duration.class)){
            Duration la = method.getAnnotation(Duration.class);
            var cur = System.currentTimeMillis();
            var obj = method.invoke(target, args);
            var now = System.currentTimeMillis();
            System.out.println(String.format("Duration: %d", (now - cur)));
            return obj;
        } else {
            return method.invoke(target, args);
        }
    }
}
