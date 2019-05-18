package com.support.monitor.plugins.spring;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;

import java.lang.reflect.Method;

/**
 * spring
 *
 * @author 江浩
 */
public class SpringAroundInterceptor implements AroundInterceptor {
    @Override
    public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

        System.out.println("spring 插件执行执行...." + clazz.getName() + "  " + method.getName());
    }

    @Override
    public Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {

        System.out.println("spring 插件执行之后...." + clazz.getName() + "  " + method.getName());
        return null;
    }

    @Override
    public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {
        System.out.println("spring 插件执行异常...." + clazz.getName() + "  " + method.getName());
    }
}
