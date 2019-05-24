package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.DefaultTraceContext;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.TraceFactory;

public class TraceContextProvider implements Provider<TraceContext> {

    private TraceFactory traceFactory;


    @Inject
    public TraceContextProvider(TraceFactory traceFactory) {
        this.traceFactory = traceFactory;
    }

    @Override
    public TraceContext get() {
        return new DefaultTraceContext(this.traceFactory);
    }
}
