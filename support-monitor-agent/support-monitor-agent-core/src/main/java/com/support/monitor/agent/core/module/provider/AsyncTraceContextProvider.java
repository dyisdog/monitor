package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.AsyncTraceContext;
import com.support.monitor.agent.core.context.trace.TraceFactory;
import com.support.monitor.agent.core.context.trace.def.DefaultAsyncTraceContext;

/**
 * @author
 */
public class AsyncTraceContextProvider implements Provider<AsyncTraceContext> {

    private TraceFactory traceFactory;

    @Inject
    public AsyncTraceContextProvider(TraceFactory traceFactory) {

        this.traceFactory = traceFactory;
    }

    @Override
    public AsyncTraceContext get() {
        return new DefaultAsyncTraceContext(this.traceFactory);
    }
}
