package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.*;
import com.support.monitor.agent.core.context.trace.recorder.RecorderFactory;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.commons.binder.Binder;

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

    private RecorderFactory recorderFactory;


    public DefaultTraceFactory(Binder<Trace> threadLocalBinder,
                               TraceIdFactory traceIdFactory,
                               SpanFactory spanFactory,
                               IdGenerator idGenerator,
                               RecorderFactory recorderFactory) {
        this.threadLocalBinder = threadLocalBinder;
        this.traceIdFactory = traceIdFactory;
        this.spanFactory = spanFactory;
        this.idGenerator = idGenerator;
        this.recorderFactory = recorderFactory;
    }


    @Override
    public Trace currentRawTraceObject() {
        final AtomicReference<Trace> reference = getReference();
        return reference.get();
    }

    @Override
    public Trace removeTraceObject() {
        final AtomicReference<Trace> reference = getReference();
        final Trace trace = reference.getAndSet(null);
        return trace;
    }


    @Override
    public Trace newTraceObject(TraceId traceId) {
        final AtomicReference<Trace> reference = getReference();
        final Span span = spanFactory.newSpan(traceId);
        final SpanEventRecorder spanEventRecorder = recorderFactory.newSpanEventRecorder(span);
        final DefaultTrace trace = new DefaultTrace(span, spanEventRecorder);
        reference.set(trace);
        return trace;
    }

    @Override
    public Trace newTraceObject(Trace trace) {
        final AtomicReference<Trace> reference = getReference();
        reference.set(trace);
        return trace;
    }

    @Override
    public Trace newAsyncTraceObject(TraceId traceId) {
        return this.newTraceObject(traceId);
    }

    @Override
    public Trace newTraceObject() {
        String id = idGenerator.transactionId();
        TraceId traceId = traceIdFactory.newTraceId(id);
        return this.newTraceObject(traceId);
    }

    @Override
    public Trace newAsyncTraceObject() {
        return this.newTraceObject();
    }

    @Override
    public AtomicReference<Trace> getReference() {
        return threadLocalBinder.get();
    }
}
