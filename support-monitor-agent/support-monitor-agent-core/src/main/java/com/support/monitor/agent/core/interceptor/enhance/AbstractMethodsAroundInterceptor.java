package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.TraceContext;

import java.lang.reflect.Method;

public abstract class AbstractMethodsAroundInterceptor implements MethodsAroundInterceptor, ConstructorInterceptor {

    private TraceContext traceContext;

    public AbstractMethodsAroundInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

        System.out.println("==> ABS" + enhancedDefine.getClass() + "   " + Thread.currentThread().getId());
    }
}
