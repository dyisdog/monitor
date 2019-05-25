package com.support.monitor.plugins.custom.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.supper.AbstractAsyncMethodAroundInterceptor;

import java.lang.reflect.Method;

public class TraceThreadExecuteInterceptor extends AbstractAsyncMethodAroundInterceptor {

    public TraceThreadExecuteInterceptor(TraceContext traceContext) {
        super(traceContext);
    }


    @Override
    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        this.print(enhancedDefine, method, result, trace);
    }
}
