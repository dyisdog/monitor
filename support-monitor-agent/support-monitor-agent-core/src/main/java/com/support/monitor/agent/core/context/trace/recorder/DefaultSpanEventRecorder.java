package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.Span;
import com.support.monitor.agent.core.context.trace.TraceId;

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

}
