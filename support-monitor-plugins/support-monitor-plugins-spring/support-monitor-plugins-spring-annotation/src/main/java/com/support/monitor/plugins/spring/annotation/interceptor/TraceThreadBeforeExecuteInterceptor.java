package com.support.monitor.plugins.spring.annotation.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;

import java.lang.reflect.Method;
import java.util.Objects;

public class TraceThreadBeforeExecuteInterceptor extends AbstractMethodAroundInterceptor {


    public TraceThreadBeforeExecuteInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        Trace trace = getTraceContext().currentRawTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        //设置当前trace 的span事件进去
        SpanEventRecorder spanEventRecorder = trace.currentSpanEventRecorder();
        enhancedDefine.setEnhancedInstanceTraceContext(spanEventRecorder);
        this.doBefore(trace, enhancedDefine, method, allArguments, parameterTypes);
    }

    @Override
    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {

    }
}
