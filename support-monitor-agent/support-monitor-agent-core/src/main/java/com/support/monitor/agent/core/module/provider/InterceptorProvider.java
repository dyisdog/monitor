package com.support.monitor.agent.core.module.provider;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;
import com.support.monitor.agent.core.interceptor.enhance.DefaultInterceptorFactory;

/**
 * 拦截器处理
 *
 * @author 江浩
 */
public class InterceptorProvider implements Provider<InterceptorFactory> {

    private TraceContext traceContext;

    @Inject
    public InterceptorProvider(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public InterceptorFactory get() {
        return new DefaultInterceptorFactory(this.traceContext);
    }
}
