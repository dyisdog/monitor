package com.support.monitor.agent.core.interceptor;

public interface InterceptorFactory {

    /**
     * 创建拦截器实例
     * <p>目前默认使用带有 {@link com.support.monitor.agent.core.context.trace.TraceContext} 参数的构造器</p>
     *
     * @param aroundInterceptorClass :
     * @return : com.support.monitor.agent.core.interceptor.AroundInterceptor
     * @author 江浩
     */
    AroundInterceptor newInterceptor(Class<? extends AroundInterceptor> aroundInterceptorClass);
}
