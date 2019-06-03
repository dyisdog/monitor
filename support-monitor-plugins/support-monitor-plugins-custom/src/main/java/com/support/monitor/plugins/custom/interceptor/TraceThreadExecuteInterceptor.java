package com.support.monitor.plugins.custom.interceptor;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractAsyncMethodAroundInterceptor;

import java.lang.reflect.Method;

public class TraceThreadExecuteInterceptor extends AbstractAsyncMethodAroundInterceptor {
    public TraceThreadExecuteInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    protected void doBefore(SofaTracerSpan sofaTracerSpan, Object enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(SofaTracerSpan sofaTracerSpan, Object enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        this.print(sofaTracerSpan, enhancedDefine, method);
    }

//    public TraceThreadExecuteInterceptor(TraceContext traceContext) {
//        super(traceContext);
//    }
//
//
//    @Override
//    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
//
//    }
//
//    @Override
//    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
//        this.print(trace);
//    }
}
