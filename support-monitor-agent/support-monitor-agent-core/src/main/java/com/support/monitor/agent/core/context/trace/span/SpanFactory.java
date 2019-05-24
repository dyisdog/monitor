package com.support.monitor.agent.core.context.trace.span;

import com.support.monitor.agent.core.context.trace.id.TraceId;

public interface SpanFactory {

    /**
     * 创建span
     *
     * @param traceId
     * @return
     */
    Span newSpan(TraceId traceId);
}
