package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.AbstractMethodsAroundInterceptor;

public class DemoPluginInterceptor extends AbstractMethodsAroundInterceptor {
    public DemoPluginInterceptor(TraceContext traceContext) {
        super(traceContext);
    }
}
