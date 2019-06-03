package com.support.monitor.agent.core.module.provider;

import com.alipay.common.tracer.core.SofaTracer;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.DefaultTraceContext;
import com.support.monitor.agent.core.context.TraceContext;

public class TraceContextProvider implements Provider<TraceContext> {

    private SofaTracer sofaTracer;

    @Inject
    public TraceContextProvider(SofaTracer sofaTracer) {
        this.sofaTracer = sofaTracer;
    }

    @Override
    public TraceContext get() {
        return new DefaultTraceContext(sofaTracer);
    }
}
