package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;

import java.lang.reflect.Method;

/**
 * spring
 *
 * @author 江浩
 */
public class DemoAroundInterceptor implements AroundInterceptor {
    @Override
    public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

        System.out.println("demo 执行之前...." + clazz.getName() + "  " + method.getName() + "  " + allArguments);
    }

    @Override
    public Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {

        System.out.println("demo 执行之后...." + clazz.getName() + "  " + method.getName());
        System.out.println(ret + "  执行结果");
        return null;
    }

    @Override
    public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {
        System.out.println("demo 插件执行异常...." + clazz.getName() + "  " + method.getName());
    }
}
