package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.AsyncTraceContext;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.TraceFactory;
import com.support.monitor.agent.core.context.trace.def.DefaultTraceContext;

public class TraceContextProvider implements Provider<TraceContext> {

    private TraceFactory traceFactory;

    private AsyncTraceContext asyncTraceContext;

    @Inject
    public TraceContextProvider(TraceFactory traceFactory, AsyncTraceContext asyncTraceContext) {
        this.traceFactory = traceFactory;
        this.asyncTraceContext = asyncTraceContext;
    }

    @Override
    public TraceContext get() {
        return new DefaultTraceContext(this.traceFactory, this.asyncTraceContext);
    }
}
