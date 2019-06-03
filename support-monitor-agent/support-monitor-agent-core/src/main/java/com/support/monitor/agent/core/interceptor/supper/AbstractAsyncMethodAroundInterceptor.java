package com.support.monitor.agent.core.interceptor.supper;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import io.opentracing.tag.Tags;

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
    public void before(Object object, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

        if (!(object instanceof EnhancedDefine)) {
            return;
        }

        EnhancedDefine enhancedDefine = (EnhancedDefine) object;
        SofaTracerSpan sofaTracerSpan = enhancedDefine.getEnhancedInstanceTraceContext();
        if (Objects.isNull(sofaTracerSpan)) {
            return;
        }
        //构建新的span
        sofaTracerSpan = (SofaTracerSpan) getTraceContext().getSofaTracer()
                .buildSpan(this.getClass().getSimpleName())
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER)
                .asChildOf(sofaTracerSpan)
                .start();

        sofaTracerSpan.setBaggageItem("className", enhancedDefine.getClass().getSimpleName());
        sofaTracerSpan.setBaggageItem("methodName", method.getName());
        getTraceContext().push(sofaTracerSpan);
        this.doBefore(sofaTracerSpan, enhancedDefine, method, allArguments, parameterTypes);

    }
}
