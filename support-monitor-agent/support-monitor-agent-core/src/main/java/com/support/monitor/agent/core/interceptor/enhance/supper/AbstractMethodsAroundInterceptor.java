package com.support.monitor.agent.core.interceptor.enhance.supper;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;
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
        Trace trace = traceContext.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        trace.traceBegin();
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

    public void log(Trace trace, EnhancedDefine enhancedDefine, Method method) {
        System.out.println("线程ID：" + Thread.currentThread().getId() + "  " +
                enhancedDefine.getClass().getSimpleName() + " " +
                method.getName() + " traceID: "
                + trace.currentSpanEventRecorder().getTraceId().id() +
                " 用时:" + (trace.currentSpanEventRecorder().getSpan().executeTime())
        );
    }

}
