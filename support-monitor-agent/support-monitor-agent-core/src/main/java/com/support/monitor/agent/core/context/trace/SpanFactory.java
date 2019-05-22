package com.support.monitor.agent.core.context.trace;

public interface SpanFactory {

    /**
     * 创建span
     *
     * @param traceId
     * @return
     */
    Span newSpan(TraceId traceId);
}
