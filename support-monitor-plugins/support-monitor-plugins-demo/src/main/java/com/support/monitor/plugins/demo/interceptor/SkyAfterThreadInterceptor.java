package com.support.monitor.plugins.demo.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.interceptor.enhance.AbstractAsyncMethodsAroundInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

import java.lang.reflect.Method;
import java.util.Objects;

public class SkyAfterThreadInterceptor extends AbstractAsyncMethodsAroundInterceptor {


    public SkyAfterThreadInterceptor(TraceContext traceContext) {
        super(traceContext);
    }


    @Override
    public void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        try {
            SpanEventRecorder spanEventRecorder = enhancedDefine.getEnhancedInstanceTraceContext();
            if (!Objects.isNull(spanEventRecorder)) {
                Trace trace = getTraceContext().newTraceObject(spanEventRecorder.getTraceId());
                System.out.println("执行: " + enhancedDefine.getClass() + " " + method.getName() + "  " + trace);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doBeforeMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doInBeforeMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }


    @Override
    public void afterMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {

    }

    @Override
    protected void doAfterMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {

    }

    @Override
    protected void doExceptionMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }

}
