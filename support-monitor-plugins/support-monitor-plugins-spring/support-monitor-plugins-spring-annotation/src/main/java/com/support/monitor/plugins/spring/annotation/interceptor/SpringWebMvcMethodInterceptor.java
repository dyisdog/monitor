package com.support.monitor.plugins.spring.annotation.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;

import java.lang.reflect.Method;
import java.util.Objects;

public class SpringWebMvcMethodInterceptor extends AbstractMethodAroundInterceptor {


    public SpringWebMvcMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }


    @Override
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        Trace trace = getTraceContext().newTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        trace.traceBegin();
        this.doBefore(trace, enhancedDefine, method, allArguments, parameterTypes);
    }

    @Override
    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {

    }
}