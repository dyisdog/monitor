package com.support.monitor.agent.core.module.provider;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.DefaultInterceptorFactory;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;

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
