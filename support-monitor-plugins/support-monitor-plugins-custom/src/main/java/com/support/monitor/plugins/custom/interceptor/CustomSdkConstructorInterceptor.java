package com.support.monitor.plugins.custom.interceptor;

import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractConstructorInterceptor;

public class CustomSdkConstructorInterceptor extends AbstractConstructorInterceptor {

    private TraceContext traceContext;

    public CustomSdkConstructorInterceptor(TraceContext traceContext) {
        super(traceContext);
    }
}
