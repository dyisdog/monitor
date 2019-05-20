package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.TraceContext;

public class ObjectBinderFactoryProvider implements Provider<ObjectBinderFactory> {

    private Provider<TraceContext> traceContextProvider;

    @Inject
    public ObjectBinderFactoryProvider(
            Provider<TraceContext> traceContextProvider) {
        this.traceContextProvider = traceContextProvider;
    }

    @Override
    public ObjectBinderFactory get() {
        return new ObjectBinderFactory(this.traceContextProvider);
    }
}
