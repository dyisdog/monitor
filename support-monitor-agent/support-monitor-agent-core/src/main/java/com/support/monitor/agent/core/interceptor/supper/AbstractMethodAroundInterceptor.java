package com.support.monitor.agent.core.interceptor.supper;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.context.trace.span.Span;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;
import com.support.monitor.agent.core.interceptor.MethodAroundInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 江浩
 */
@Getter
public abstract class AbstractMethodAroundInterceptor implements MethodAroundInterceptor {

    private TraceContext traceContext;

    public AbstractMethodAroundInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        Trace trace = traceContext.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        trace.traceBegin(SpanEvent.builder()
                .eventTarget(enhancedDefine.getClass().getName())
                .eventMethod(method.getName())
                .build());

        this.doBefore(trace, enhancedDefine, method, allArguments, parameterTypes);
    }

    /**
     * do before
     *
     * @param trace          :
     * @param enhancedDefine :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @return : void
     * @author 江浩
     */
    protected abstract void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes);

    @Override
    public void after(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        Trace trace = traceContext.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        trace.traceEnd();

        this.doAfter(trace, enhancedDefine, method, allArguments, parameterTypes, result);
    }

    /**
     * do after
     *
     * @param trace          :
     * @param enhancedDefine :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param result         :
     * @return : void
     * @author 江浩
     */
    protected abstract void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result);

    @Override
    public void exception(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }

    protected void print(Trace trace) {

        SpanEventRecorder spanEventRecorder = trace.currentSpanEventRecorder();
        Span span = spanEventRecorder.getFirstSpanEvent();
        SpanEvent spanEvent = span.getSpanEvent();

        System.out.println("threadId: " + Thread.currentThread().getId()
                + "\t className: " + spanEvent.getEventTarget()
                + "\t methodName: " + spanEvent.getEventMethod()
                + "\t depth: " + span.getDepth()
                + "\t traceId: " + span.getTraceId()
                + "\t spanId: " + span.getId()
                + "\t time: " + span.executeTime()
        );
    }
}
