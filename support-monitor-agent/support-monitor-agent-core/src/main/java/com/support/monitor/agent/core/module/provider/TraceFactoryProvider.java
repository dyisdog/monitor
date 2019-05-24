package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.*;
import com.support.monitor.agent.core.context.trace.def.DefaultTraceFactory;
import com.support.monitor.agent.core.context.trace.recorder.RecorderFactory;
import com.support.monitor.commons.binder.Binder;

/**
 * @author 江浩
 */
public class TraceFactoryProvider implements Provider<TraceFactory> {

    private Binder<Trace> binder;

    private TraceIdFactory traceIdFactory;

    private IdGenerator idGenerator;

    private SpanFactory spanFactory;

    private RecorderFactory recorderFactory;


    @Inject
    public TraceFactoryProvider(Binder<Trace> binder,
                                TraceIdFactory traceIdFactory,
                                IdGenerator idGenerator,
                                SpanFactory spanFactory,
                                RecorderFactory recorderFactory) {
        this.binder = binder;
        this.traceIdFactory = traceIdFactory;
        this.idGenerator = idGenerator;
        this.spanFactory = spanFactory;
        this.recorderFactory = recorderFactory;
    }

    @Override
    public TraceFactory get() {

        return new DefaultTraceFactory(
                this.binder,
                this.traceIdFactory,
                this.spanFactory,
                this.idGenerator,
                this.recorderFactory
        );
    }
}
