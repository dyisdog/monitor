package com.support.monitor.plugins.custom.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;

import java.lang.reflect.Method;
import java.util.Objects;

public class TraceThreadExecuteInterceptor implements MethodsAroundInterceptor {

    private TraceContext traceContext;

    public TraceThreadExecuteInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        SpanEventRecorder spanEventRecorder = enhancedDefine.getEnhancedInstanceTraceContext();
        if (!Objects.isNull(spanEventRecorder)) {
            Trace trace = traceContext.newTraceObject(spanEventRecorder.getTraceId());
            trace.traceBegin();
        }
    }

    @Override
    public void afterMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        Trace trace = traceContext.currentRawTraceObject();
        print(enhancedDefine, method, ret, trace);
    }

    @Override
    public void exceptionMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }

    protected void print(EnhancedDefine enhancedDefine, Method method, Object ret, Trace trace) {
        System.out.println(Thread.currentThread().getId() + "  " + enhancedDefine.getClass() + "  "
                + method.getName() + " 执行结果:"
                + ret + "  traceId: "
                + trace.currentSpanEventRecorder().getTraceId().id()
                + "  time: " + (trace.currentSpanEventRecorder().getSpan().executeTime()));
    }
}
