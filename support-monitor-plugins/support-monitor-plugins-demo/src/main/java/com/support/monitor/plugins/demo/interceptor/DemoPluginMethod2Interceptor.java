package com.support.monitor.plugins.demo.interceptor;

import com.support.monitor.agent.core.interceptor.PluginInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;

public class DemoPluginMethod2Interceptor implements PluginInterceptor {
    @Override
    public ElementMatcher<? super TypeDescription> classInterceptor() {
        return ElementMatchers.nameStartsWithIgnoreCase("com.example.demo");
    }


    @Override
    public ElementMatcher<? super MethodDescription> methodInterceptor() {
        return ElementMatchers.named("test2");
    }

    @Override
    public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

        System.out.println("method: " + method.getName() + " before");
    }

    @Override
    public Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        System.out.println("method: " + method.getName() + " after");
        return ret;
    }

    @Override
    public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }
}
