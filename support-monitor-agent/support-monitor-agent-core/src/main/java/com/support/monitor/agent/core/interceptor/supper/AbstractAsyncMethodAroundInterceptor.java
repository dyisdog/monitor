package com.support.monitor.agent.core.interceptor.supper;

import com.alipay.common.tracer.core.context.trace.SofaTraceContext;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 江浩
 */
public abstract class AbstractAsyncMethodAroundInterceptor extends AbstractMethodAroundInterceptor {

    public AbstractAsyncMethodAroundInterceptor(TraceContext traceContext) {
        super(traceContext);
    }


    @Override
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
//        TraceRootRecorder traceIdRecorder = enhancedDefine.getEnhancedInstanceTraceContext();
//        Trace trace = getTraceContext().newTraceObject(traceIdRecorder);
//        trace.traceBegin(SpanEvent.builder()
//                .args(allArguments)
//                .eventTarget(enhancedDefine.getClass().getName())
//                .eventMethod(method.getName())
//                .build());
//
//        this.doBefore(trace, enhancedDefine, method, allArguments, parameterTypes);

        SofaTraceContext sofaTraceContext = enhancedDefine.getEnhancedInstanceTraceContext();
        if (Objects.isNull(sofaTraceContext)) {
            return;
        }
        SofaTracerSpan sofaTracerSpan = sofaTraceContext.getCurrentSpan();
        //builder form parent
        sofaTracerSpan.finish();

        this.doBefore(sofaTracerSpan, enhancedDefine, method, allArguments, parameterTypes);

    }
}
