package com.magicworldz.leetcode.common.duration;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DurationMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        if(method.isAnnotationPresent(Duration.class)){
            //Duration la = method.getAnnotation(Duration.class);
            var cur = System.currentTimeMillis();
            var ret = proxy.invokeSuper(obj, args);
            var now = System.currentTimeMillis();
            System.out.println(String.format("Duration: %dms", (now - cur)));
            return ret;
        } else {
            return proxy.invokeSuper(obj, args);
        }
    }
}
