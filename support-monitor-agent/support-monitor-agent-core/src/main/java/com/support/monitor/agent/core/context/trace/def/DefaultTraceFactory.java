package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.*;
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
    public Trace continueTraceObject(TraceId traceId) {
        final AtomicReference<Trace> reference = getReference();
        final Span span = spanFactory.newSpan(traceId);
//        final SpanRecorder spanRecorder = recorderFactory.newSpanRecorder(span, traceId.isRoot(), samplingEnable);
//        final WrappedSpanEventRecorder wrappedSpanEventRecorder = recorderFactory.newWrappedSpanEventRecorder(traceRoot);
//        final ActiveTraceHandle handle = registerActiveTrace(traceRoot);
        //callStack, storage, asyncContextFactory, samplingEnable, spanRecorder, wrappedSpanEventRecorder, handle
        final DefaultTrace trace = new DefaultTrace(span);
        reference.set(trace);
        return trace;
    }

    @Override
    public Trace continueTraceObject(Trace trace) {
        final AtomicReference<Trace> reference = getReference();
        reference.set(trace);
        return trace;
    }

    @Override
    public Trace continueAsyncTraceObject(TraceId traceId) {
//        final AtomicReference<Trace> reference = getReference();
//        final Trace trace = reference.getAndSet(null);
//        return trace;
        return this.continueTraceObject(traceId);
    }

    @Override
    public Trace newTraceObject() {
        String id = idGenerator.transactionId();
        TraceId traceId = traceIdFactory.newTraceId(id);
        return this.continueTraceObject(traceId);
    }

    @Override
    public Trace newAsyncTraceObject() {
//        final AtomicReference<Trace> reference = getReference();
//        final Trace trace = this.baseTraceFactory.continueAsyncTraceObject(traceId);
//        reference.set(trace);
//        return trace;

        return this.newTraceObject();
    }

    @Override
    public AtomicReference<Trace> getReference() {
        return threadLocalBinder.get();
    }
}
