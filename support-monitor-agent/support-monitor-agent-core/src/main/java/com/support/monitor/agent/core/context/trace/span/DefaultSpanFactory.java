package com.support.monitor.agent.core.context.trace.span;

import com.support.monitor.agent.core.context.trace.id.IdGenerator;
import com.support.monitor.agent.core.context.trace.id.TraceId;

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
    public Span newSpan(TraceId traceId, SpanEvent spanEvent) {
        final Span span = new Span(idGenerator.uuid(), traceId, spanEvent);
        span.markBeforeTime();
        return span;
    }
}
