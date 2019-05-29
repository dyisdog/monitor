package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.id.IdGenerator;
import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.agent.core.context.trace.id.TraceIdFactory;
import com.support.monitor.agent.core.context.trace.recorder.RecorderFactory;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.context.trace.recorder.TraceRootRecorder;
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


    private IdGenerator idGenerator;

    private RecorderFactory recorderFactory;


    public DefaultTraceFactory(Binder<Trace> threadLocalBinder,
                               TraceIdFactory traceIdFactory,
                               IdGenerator idGenerator,
                               RecorderFactory recorderFactory) {
        this.threadLocalBinder = threadLocalBinder;
        this.traceIdFactory = traceIdFactory;
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
        return reference.getAndSet(null);
    }


    @Override
    public Trace newTraceObject(TraceRootRecorder traceRootRecorder) {
        TraceId traceId = traceRootRecorder.getTraceId();
        SpanEventRecorder spanEventRecorder = traceRootRecorder.currentSpanEventRecorder();
        final AtomicReference<Trace> reference = getReference();
        //每一个trace 对应一个span事件记录器
        final SpanEventRecorder newSpanEventRecorder = recorderFactory.newSpanEventRecorder();
        newSpanEventRecorder.resetCurrentSpan(spanEventRecorder.currentSpan());
        final TraceRootRecorder newTraceRootRecorder = recorderFactory.newTraceRootRecorder(traceId, spanEventRecorder);
        //default trace
        final DefaultTrace trace = new DefaultTrace(newTraceRootRecorder, newSpanEventRecorder);
        reference.set(trace);
        return trace;
    }

    @Override
    public Trace newTraceObject(TraceId traceId) {
        final AtomicReference<Trace> reference = getReference();
        //每一个trace 对应一个span事件记录器
        final SpanEventRecorder spanEventRecorder = recorderFactory.newSpanEventRecorder();
        final TraceRootRecorder newTraceRootRecorder = recorderFactory.newTraceRootRecorder(traceId, spanEventRecorder);
        //default trace
        final DefaultTrace trace = new DefaultTrace(newTraceRootRecorder, spanEventRecorder);
        reference.set(trace);
        return trace;
    }


    @Override
    public Trace newTraceObject() {
        String id = idGenerator.transactionId();
        TraceId traceId = traceIdFactory.newTraceId(id);
        return this.newTraceObject(traceId);
    }


    @Override
    public AtomicReference<Trace> getReference() {
        return threadLocalBinder.get();
    }
}
