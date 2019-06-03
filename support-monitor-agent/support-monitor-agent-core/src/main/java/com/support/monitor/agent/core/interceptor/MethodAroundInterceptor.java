package com.support.monitor.agent.core.interceptor;

import java.lang.reflect.Method;

/**
 * 方法环绕拦截器
 *
 * @author 江浩
 */
public interface MethodAroundInterceptor {


    /**
     * 方法执行之前
     *
     * @param target
     * @param method
     * @param allArguments
     * @param parameterTypes TODO MethodInterceptResult result
     */
    void before(Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes);

    /**
     * 方法执行之后
     *
     * @param target         :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param result         :
     * @return : java.lang.Object
     * @author 江浩
     */
    void after(Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result);

    /**
     * 执行异常
     *
     * @param target         :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param t              :
     * @return : void
     * @author 江浩
     */
    void exception(Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes,
                   Throwable t);
}
