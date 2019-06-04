package com.support.monitor.plugins.spring.annotation.interceptor;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractAsyncMethodAroundInterceptor;

import java.lang.reflect.Method;

/**
 * 线程设置之后执行拦截
 *
 * @author 江浩
 */
public class AfterTraceThreadExecuteInterceptor extends AbstractAsyncMethodAroundInterceptor {
    public AfterTraceThreadExecuteInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    protected void doBefore(SofaTracerSpan sofaTracerSpan, Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(SofaTracerSpan sofaTracerSpan, Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
    }

//    public AfterTraceThreadExecuteInterceptor(TraceContext traceContext) {
//        super(traceContext);
//    }
//
//
//    @Override
//    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
//    }
//
//    @Override
//    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
//        this.print(trace);
//    }
}