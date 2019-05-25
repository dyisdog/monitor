package com.support.monitor.plugins.spring.annotation.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;

import java.lang.reflect.Method;

public abstract class AbstractMethodInterceptor implements MethodsAroundInterceptor {

    protected TraceContext traceContext;

    public AbstractMethodInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    protected void print(EnhancedDefine enhancedDefine, Method method, Object ret, Trace trace) {
        System.out.println(Thread.currentThread().getId() + "  " + enhancedDefine.getClass() + "  "
                + method.getName() + " 执行结果:"
                + ret + "  traceId: "
                + trace.currentSpanEventRecorder().getTraceId().id()
                + "  time: " + (trace.currentSpanEventRecorder().getSpan().executeTime()));
    }
}
