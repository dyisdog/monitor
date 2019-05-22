package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.IdGenerator;
import com.support.monitor.agent.core.context.trace.Span;
import com.support.monitor.agent.core.context.trace.SpanFactory;
import com.support.monitor.agent.core.context.trace.TraceId;

/**
 * span default handle factory
 *
 * @author
 */
public class DefaultSpanFactory implements SpanFactory {


    private IdGenerator idGenerator;

    public DefaultSpanFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Span newSpan(TraceId traceId) {
        final Span span = new Span(idGenerator.uuid(), traceId);
        span.markBeforeTime();
        return span;
    }
}
