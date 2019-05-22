package com.support.monitor.agent.core.interceptor.enhance;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author 江浩
 */
public interface StaticMethodsInterceptor {

    //MethodInterceptResult result
    void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes);

    void afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret);

    void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes,
                               Throwable t);
}
