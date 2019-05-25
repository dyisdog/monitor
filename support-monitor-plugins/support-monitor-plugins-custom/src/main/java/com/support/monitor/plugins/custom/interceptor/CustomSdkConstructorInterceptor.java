package com.support.monitor.plugins.custom.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.interceptor.enhance.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

import java.util.Objects;

public class CustomSdkConstructorInterceptor implements ConstructorInterceptor {

    private TraceContext traceContext;

    public CustomSdkConstructorInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void onConstruct(EnhancedDefine enhancedDefine, Object[] allArguments) {
        Trace trace = traceContext.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        //设置当前trace 的span事件进去
        SpanEventRecorder spanEventRecorder = trace.currentSpanEventRecorder();
        System.out.println("thread: " + Thread.currentThread().getId() + "  " + spanEventRecorder.getTraceId().id());
        enhancedDefine.setEnhancedInstanceTraceContext(spanEventRecorder);
    }
}
