package com.support.monitor.agent.core.interceptor;

import java.lang.reflect.Method;

/**
 * 执行拦截器
 *
 * @author 江浩
 */
public interface AroundInterceptor {


    /**
     * 方法执行之前
     *
     * @param clazz
     * @param method
     * @param allArguments
     * @param parameterTypes TODO MethodInterceptResult result
     */
    void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes);

    /**
     * 方法执行之后
     *
     * @param clazz          :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param ret            :
     * @return : java.lang.Object
     * @author 江浩
     */
    Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret);

    /**
     * 执行异常
     *
     * @param clazz          :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param t              :
     * @return : void
     * @author 江浩
     */
    void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes,
                               Throwable t);
}
