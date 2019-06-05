package com.support.monitor.plugins.custom.interceptor;

import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.async.AbstractAsyncMethodAroundInterceptor;

public class CustomSdkPluginMethodInterceptor extends AbstractAsyncMethodAroundInterceptor {
    public CustomSdkPluginMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }


}
