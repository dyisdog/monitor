package com.support.monitor.plugins.spring.annotation;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.AbstractMethodsAroundInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

import java.lang.reflect.Method;

/**
 * @author 江浩
 */
public class SpringAnnotationMethodInterceptor extends AbstractMethodsAroundInterceptor {
    public SpringAnnotationMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    protected void doBeforeMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {


    }

    @Override
    protected void doAfterMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        System.out.println(enhancedDefine.getClass() + " " + method.getName() + " trace"
                + trace.currentSpanEventRecorder().getTraceId().id() + " 用时:" + (trace.currentSpanEventRecorder().getSpan().getEndTime() - trace.currentSpanEventRecorder().getSpan().getStartTime())
        );
    }

    @Override
    protected void doExceptionMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }

}
