package com.support.monitor.agent.core.interceptor.supper;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.TraceRootRecorder;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

import java.lang.reflect.Method;

/**
 * @author 江浩
 */
public abstract class AbstractAsyncMethodAroundInterceptor extends AbstractMethodAroundInterceptor {

    public AbstractAsyncMethodAroundInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        TraceRootRecorder traceIdRecorder = enhancedDefine.getEnhancedInstanceTraceIdRecorder();
        Trace trace = getTraceContext().newTraceObject(traceIdRecorder);

        trace.traceBegin(SpanEvent.builder()
                .args(allArguments)
                .eventTarget(enhancedDefine.getClass().getName())
                .eventMethod(method.getName())
                .build());

        this.doBefore(trace, enhancedDefine, method, allArguments, parameterTypes);
    }
}
