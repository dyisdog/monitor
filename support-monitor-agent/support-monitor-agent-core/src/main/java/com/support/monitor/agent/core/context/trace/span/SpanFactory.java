package com.support.monitor.agent.core.context.trace.span;

import com.support.monitor.agent.core.context.trace.id.TraceId;

/**
 * span 构建工厂
 *
 * @author 江浩
 */
public interface SpanFactory {

    /**
     * 创建span
     *
     * @param spanEvent
     * @param traceId
     * @return
     */
    Span newSpan(TraceId traceId, SpanEvent spanEvent);

    /**
     * 根据
     *
     * @param traceId   :
     * @param spanEvent :
     * @param preSpan   :
     * @return : com.support.monitor.agent.core.context.trace.span.Span
     * @author 江浩
     */
    Span newSpan(TraceId traceId, SpanEvent spanEvent, Span preSpan);
}
