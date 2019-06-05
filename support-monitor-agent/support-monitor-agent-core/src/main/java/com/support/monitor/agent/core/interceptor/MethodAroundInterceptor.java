package com.support.monitor.agent.core.interceptor;

/**
 * 方法环绕拦截器
 *
 * @author 江浩
 */
public interface MethodAroundInterceptor {


    /**
     * 之前
     *
     * @param interceptContext :
     * @return : void
     * @author 江浩
     */
    void before(InterceptContext interceptContext);

    /**
     * 执行之后
     *
     * @param interceptContext :
     * @param result           :
     * @param throwable        :
     * @return : void
     * @author 江浩
     */
    void after(InterceptContext interceptContext, Object result, Throwable throwable);

}
