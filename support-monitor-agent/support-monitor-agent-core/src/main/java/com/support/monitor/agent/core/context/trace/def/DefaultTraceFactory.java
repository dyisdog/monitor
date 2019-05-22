package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.*;
import com.support.monitor.commons.binder.Binder;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * default
 *
 * @author
 */
public class DefaultTraceFactory implements TraceFactory {

    private final Binder<Trace> threadLocalBinder;

    private TraceIdFactory traceIdFactory;

    private SpanFactory spanFactory;

    private IdGenerator idGenerator;

    public DefaultTraceFactory(Binder<Trace> threadLocalBinder,
                               TraceIdFactory traceIdFactory,
                               SpanFactory spanFactory,
                               IdGenerator idGenerator) {
        this.threadLocalBinder = threadLocalBinder;
        this.traceIdFactory = traceIdFactory;
        this.spanFactory = spanFactory;
        this.idGenerator = idGenerator;
    }

    @Override
    public Trace currentTraceObject() {
        final AtomicReference<Trace> reference = getReference();
        return reference.get();
    }

    @Override
    public Trace removeTraceObject() {
        final AtomicReference<Trace> reference = getReference();
        return reference.updateAndGet(trace -> null);
    }

    @Override
    public Trace newTraceObject() {
        final AtomicReference<Trace> reference = getReference();
        final Trace trace = this.newTrace();
        bind(reference, trace);
        return trace;
    }

    @Override
    public Trace newTraceObject(TraceId traceId) {
        if (Objects.isNull(traceId)) {
            traceId = getTraceId();
        }
        Span span = spanFactory.newSpan(traceId);
        DefaultTrace defaultTrace = new DefaultTrace(span);
        bind(getReference(), defaultTrace);
        return defaultTrace;
    }

    @Override
    public AtomicReference<Trace> getReference() {
        return threadLocalBinder.get();
    }

    private void bind(AtomicReference<Trace> reference, Trace trace) {
        reference.set(trace);
    }

    private Trace newTrace() {
        TraceId traceId = getTraceId();
        return newTraceObject(traceId);
    }

    private TraceId getTraceId() {
        String transactionId = idGenerator.transactionId();
        return traceIdFactory.newTraceId(transactionId);
    }

}
