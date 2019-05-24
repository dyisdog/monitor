package com.support.monitor.plugins.demo.interceptor;

import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;

import java.lang.reflect.Method;

public class ServiceInterceptor implements MethodsAroundInterceptor {

    private TraceContext traceContext;

    public ServiceInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }


    @Override
    public void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

        System.out.println("currt: " + enhancedDefine.getClass() + " " + method.getName() + "  " + traceContext.currentRawTraceObject());
    }
}
