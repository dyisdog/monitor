package com.support.monitor.plugins.demo.plugin;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;

import java.lang.reflect.Method;

/**
 * demo插件第一个测试方法拦截
 *
 * @author 江浩
 */
public class DemoPluginMethod1AroundInterceptor implements AroundInterceptor {
    @Override
    public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

        System.out.println("method: " + method.getName());
    }

    @Override
    public Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        return null;
    }

    @Override
    public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }
}
