package com.support.monitor.agent.core.context;

import com.alipay.common.tracer.core.SofaTracer;
import com.alipay.common.tracer.core.context.trace.SofaTracerThreadLocalTraceContext;
import lombok.Getter;

@Getter
public class DefaultTraceContext extends SofaTracerThreadLocalTraceContext implements TraceContext {

    private SofaTracer sofaTracer;

    public DefaultTraceContext(SofaTracer sofaTracer) {
        this.sofaTracer = sofaTracer;
    }

    @Override
    public SofaTracer getSofaTracer() {
        return this.sofaTracer;
    }
}
