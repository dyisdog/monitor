package com.support.monitor.plugins.spring.interceptor;

import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;

public class SpringPluginMethodInterceptor extends AbstractMethodAroundInterceptor {

    public SpringPluginMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }


}
