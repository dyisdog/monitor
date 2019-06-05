package com.support.monitor.plugins.spring.annotation.interceptor;

import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;

public class SpringPluginMethodInterceptor extends AbstractMethodAroundInterceptor {

    public SpringPluginMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }
}
