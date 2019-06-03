package com.support.monitor.plugins.spring.annotation.interceptor;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;

import java.lang.reflect.Method;

public class SpringBeanMethodInterceptor extends AbstractMethodAroundInterceptor {


    public SpringBeanMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    protected void doBefore(SofaTracerSpan sofaTracerSpan, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(SofaTracerSpan sofaTracerSpan, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        super.print(sofaTracerSpan, enhancedDefine, method);
    }
}
