package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.agent.core.context.trace.span.Span;

/**
 * @author 江浩
 */
public class DefaultSpanEventRecorder implements SpanEventRecorder {

    private Span span;

    public DefaultSpanEventRecorder(Span span) {
        this.span = span;
    }

    @Override
    public TraceId getTraceId() {
        return span.getTraceId();
    }

    @Override
    public boolean isAsync() {
        return span.isAsync();
    }

    @Override
    public Span getSpan() {
        return this.span;
    }

    @Override
    public void markStartTime() {
        this.span.setStartTime(System.currentTimeMillis());
    }

    @Override
    public void markEndTime() {
        this.span.setEndTime(System.currentTimeMillis());
    }

}
