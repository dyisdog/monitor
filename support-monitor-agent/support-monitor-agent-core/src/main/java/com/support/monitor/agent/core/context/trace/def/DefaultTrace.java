package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.Span;
import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceId;

/**
 * default
 *
 * @author
 */
public class DefaultTrace implements Trace {

    private Span span;

    public DefaultTrace(Span span) {
        this.span = span;
    }

    @Override
    public TraceId getTraceId() {
        return this.span.getTraceId();
    }

    @Override
    public Span getSpan() {
        return this.span;
    }

}
