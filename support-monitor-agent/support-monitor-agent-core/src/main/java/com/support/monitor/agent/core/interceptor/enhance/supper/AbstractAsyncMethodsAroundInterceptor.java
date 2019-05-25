package com.support.monitor.agent.core.interceptor.enhance.supper;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author 江浩
 */
@Slf4j
public abstract class AbstractAsyncMethodsAroundInterceptor extends AbstractMethodsAroundInterceptor {


    public AbstractAsyncMethodsAroundInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        SpanEventRecorder spanEventRecorder = enhancedDefine.getEnhancedInstanceTraceContext();
        Trace trace = getTraceContext().newTraceObject(spanEventRecorder.getTraceId());
        this.doBeforeMethod(trace, enhancedDefine, method, allArguments, parameterTypes);
    }


    @Override
    public void afterMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        final Trace trace = getTraceContext().currentRawTraceObject();
        if (trace == null) {
            return;
        }
        this.doAfterMethod(trace, enhancedDefine, method, allArguments, parameterTypes, ret);
    }

}
