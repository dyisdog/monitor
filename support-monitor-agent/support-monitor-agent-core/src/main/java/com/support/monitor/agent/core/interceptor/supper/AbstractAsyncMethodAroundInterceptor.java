package com.support.monitor.agent.core.interceptor.supper;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 江浩
 */
public abstract class AbstractAsyncMethodAroundInterceptor extends AbstractMethodAroundInterceptor {

    public AbstractAsyncMethodAroundInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        SpanEventRecorder spanEventRecorder = enhancedDefine.getEnhancedInstanceTraceContext();
        Trace trace = getTraceContext().newTraceObject(Objects.isNull(spanEventRecorder) ? null : spanEventRecorder.getTraceId());
        this.doBefore(trace, enhancedDefine, method, allArguments, parameterTypes);
    }
}
