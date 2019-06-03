package com.support.monitor.plugins.spring.annotation.interceptor;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;

import java.lang.reflect.Method;

public class SpringWebMvcMethodInterceptor extends AbstractMethodAroundInterceptor {
    public SpringWebMvcMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }


    @Override
    protected void doBefore(SofaTracerSpan sofaTracerSpan, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
    }

    @Override
    protected void doAfter(SofaTracerSpan sofaTracerSpan, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        super.print(sofaTracerSpan, enhancedDefine, method);
    }

//
//    public SpringWebMvcMethodInterceptor(TraceContext traceContext) {
//        super(traceContext);
//    }
//
//
//    @Override
//    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
//
//        //TODO 入口参数需要调整
//        Trace trace = getTraceContext().getOrNewTraceObject();
//        if (Objects.isNull(trace)) {
//            return;
//        }
//        trace.traceBegin(SpanEvent.builder()
//                .eventTarget(enhancedDefine.getClass().getName())
//                .eventMethod(method.getName())
//                .args(allArguments)
//                .build());
//        this.doBefore(trace, enhancedDefine, method, allArguments, parameterTypes);
//    }
//
//    @Override
//    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
//
//    }
//
//    @Override
//    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
//        print(trace);
//}
}
