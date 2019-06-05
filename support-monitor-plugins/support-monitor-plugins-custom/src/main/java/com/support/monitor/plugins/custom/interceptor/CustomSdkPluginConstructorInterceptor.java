package com.support.monitor.plugins.custom.interceptor;

import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractConstructorInterceptor;

public class CustomSdkPluginConstructorInterceptor extends AbstractConstructorInterceptor {
    public CustomSdkPluginConstructorInterceptor(TraceContext traceContext) {
        super(traceContext);
    }
}
