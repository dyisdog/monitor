package com.support.monitor.agent.core.interceptor.enhance;


public interface InterceptorFactory {

    /**
     * 创建拦截器实例
     * <p>目前默认使用带有 {@link com.support.monitor.agent.core.context.trace.TraceContext} 参数的构造器</p>
     *
     * @param aroundInterceptorClass :
     * @return : com.support.monitor.agent.core.interceptor.AroundInterceptor
     * @author 江浩
     */
    MethodsAroundInterceptor newMethodsInterceptor(Class<? extends MethodsAroundInterceptor> aroundInterceptorClass);

    ConstructorInterceptor newConstructorInterceptor(Class<? extends ConstructorInterceptor> constructorInterceptorClass);
}
