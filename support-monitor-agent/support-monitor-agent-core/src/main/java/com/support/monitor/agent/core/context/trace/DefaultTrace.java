package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.context.trace.recorder.TraceIdRecorder;
import com.support.monitor.agent.core.context.trace.span.Span;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;

/**
 * default
 *
 * @author
 */
public class DefaultTrace implements Trace {

    private TraceIdRecorder traceIdRecorder;

    private SpanEventRecorder spanEventRecorder;

    public DefaultTrace(TraceIdRecorder traceIdRecorder, SpanEventRecorder spanEventRecorder) {
        this.traceIdRecorder = traceIdRecorder;
        this.spanEventRecorder = spanEventRecorder;
    }


    @Override
    public TraceIdRecorder currentTraceIdRecorder() {
        return this.traceIdRecorder;
    }

    @Override
    public SpanEventRecorder currentSpanEventRecorder() {
        return this.spanEventRecorder;
    }

    @Override
    public void traceBegin(SpanEvent spanEvent) {
        //recorder traceId
        TraceId traceId = traceIdRecorder.getTraceId();
        spanEventRecorder.startSpanEventBlock(spanEvent, traceId);
    }

    @Override
    public Span traceEnd() {
        //同一个线程中包含同一个traceId
        return spanEventRecorder.endSpanEventBlock(traceIdRecorder.getTraceId());
    }


}
