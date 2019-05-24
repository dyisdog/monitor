package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 江浩
 */
@Slf4j
@Getter
public abstract class AbstractMethodsAroundInterceptor implements MethodsAroundInterceptor {

    private final TraceContext traceContext;

    public AbstractMethodsAroundInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }


    @Override
    public void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        try {
            SpanEventRecorder spanEventRecorder = enhancedDefine.getEnhancedInstanceTraceContext();
            TraceId traceId = Objects.isNull(spanEventRecorder) ? null : spanEventRecorder.getTraceId();
            Trace trace = getTraceContext().newTraceObject(traceId);
            this.doBeforeMethod(trace, enhancedDefine, method, allArguments, parameterTypes);
            trace.traceBegin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void doBeforeMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes);

    @Override
    public void afterMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        Trace trace = traceContext.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        trace.traceEnd();
        this.doAfterMethod(trace, enhancedDefine, method, allArguments, parameterTypes, ret);

    }

    protected abstract void doAfterMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret);

    @Override
    public void exceptionMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

        Trace trace = traceContext.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }

        this.doExceptionMethod(trace, enhancedDefine, method, allArguments, parameterTypes, t);
    }

    protected abstract void doExceptionMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t);
}
