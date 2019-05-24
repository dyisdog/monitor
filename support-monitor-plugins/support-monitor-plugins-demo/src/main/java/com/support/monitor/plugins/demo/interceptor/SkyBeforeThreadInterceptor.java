package com.support.monitor.plugins.demo.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.interceptor.enhance.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class SkyBeforeThreadInterceptor implements ConstructorInterceptor {

    private TraceContext traceContext;

    public SkyBeforeThreadInterceptor(TraceContext traceContext) {
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
        enhancedDefine.setEnhancedInstanceTraceContext(spanEventRecorder);
        System.out.println("async: " + enhancedDefine.getClass());
    }
}
