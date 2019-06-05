package com.support.monitor.agent.core.interceptor.supper;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

import java.util.Objects;

/**
 * @author 江浩
 */
public class AbstractConstructorInterceptor implements ConstructorInterceptor {

    protected TraceContext traceContext;

    public AbstractConstructorInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void onConstruct(EnhancedDefine enhancedDefine, Object[] allArguments) {
        SofaTracerSpan sofaTracerSpan = traceContext.getCurrentSpan();
        if (!Objects.isNull(enhancedDefine)) {
            enhancedDefine.setEnhancedInstanceTraceContext(sofaTracerSpan);
        }
    }
}
