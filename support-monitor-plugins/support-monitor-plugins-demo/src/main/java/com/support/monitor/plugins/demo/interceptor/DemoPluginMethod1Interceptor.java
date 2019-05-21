package com.support.monitor.plugins.demo.interceptor;

import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.AbstractAroundInterceptor;

public class DemoPluginMethod1Interceptor extends AbstractAroundInterceptor {

    public DemoPluginMethod1Interceptor(TraceContext traceContext) {
        super(traceContext);
    }


}
