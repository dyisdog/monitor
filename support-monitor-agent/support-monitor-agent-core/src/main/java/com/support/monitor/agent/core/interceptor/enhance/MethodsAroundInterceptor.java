package com.support.monitor.agent.core.interceptor.enhance;

import java.lang.reflect.Method;

/**
 * 执行拦截器
 *
 * @author 江浩
 */
public interface MethodsAroundInterceptor {


    /**
     * 方法执行之前
     *
     * @param enhancedDefine
     * @param method
     * @param allArguments
     * @param parameterTypes TODO MethodInterceptResult result
     */
    default void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
    }

    /**
     * 方法执行之后
     *
     * @param enhancedDefine :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param ret            :
     * @return : java.lang.Object
     * @author 江浩
     */
    default void afterMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
    }

    /**
     * 执行异常
     *
     * @param enhancedDefine :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param t              :
     * @return : void
     * @author 江浩
     */
    default void handleMethodException(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes,
                                       Throwable t) {

    }
}
