package com.support.monitor.agent.core.module.provider;


import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.DefaultTraceContext;
import com.support.monitor.agent.core.context.trace.TraceContext;

public class TraceContextProvider implements Provider<TraceContext> {

    @Override
    public TraceContext get() {
        return new DefaultTraceContext();
    }
}
