package com.support.monitor.agent.core.context.trace.span;

import com.support.monitor.agent.core.context.trace.id.IdGenerator;
import com.support.monitor.agent.core.context.trace.id.TraceId;

import java.util.Objects;

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
        return this.newSpan(traceId, spanEvent, null);
    }

    @Override
    public Span newSpan(TraceId traceId, SpanEvent spanEvent, Span preSpan) {
        final Span span = new Span(idGenerator.uuid(), traceId, Objects.isNull(preSpan) ? null : preSpan.getId(), spanEvent);
        span.markBeforeTime();
        return span;

    }
}
