package com.support.monitor.agent.core.module.provider;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.DefaultTraceContext;
import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.commons.binder.Binder;

/**
 * @author 江浩
 */
public class TraceContextProvider implements Provider<TraceContext> {

    private Binder<Trace> binder;

    @Inject
    public TraceContextProvider(Binder<Trace> binder) {
        this.binder = binder;
    }


    @Override
    public TraceContext get() {
        return new DefaultTraceContext(this.binder);
    }
}
