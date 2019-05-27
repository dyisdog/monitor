package com.support.monitor.plugins.spring.annotation.interceptor;

import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractConstructorInterceptor;

public class SpringAsyncConstructorInterceptor extends AbstractConstructorInterceptor {

    private TraceContext traceContext;

    public SpringAsyncConstructorInterceptor(TraceContext traceContext) {
        super(traceContext);
    }
}
