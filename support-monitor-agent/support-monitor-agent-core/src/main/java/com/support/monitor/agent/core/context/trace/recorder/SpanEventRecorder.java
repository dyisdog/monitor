package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.agent.core.context.trace.span.Span;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;

/**
 * trace span 时间记录器
 * <p>
 * 一个trace 一个span 记录器，该记录器会记录很多span信息
 * </p>
 *
 * @author 江浩
 */

public interface SpanEventRecorder {

    /**
     * span event 开始记录
     *
     * @param spanEvent
     * @param traceId   :
     * @return : void
     * @author 江浩
     */
    void spanEventBegin(SpanEvent spanEvent, TraceId traceId);

    /**
     * span event 结束记录
     *
     * @param traceId :
     * @return : void
     * @author 江浩
     */
    Span spanEventEnd(TraceId traceId);

    /**
     * 当前的span信息
     *
     * @return : com.support.monitor.agent.core.context.trace.span.Span
     * @author 江浩
     */
    Span currentSpan();

    void resetCurrentSpan(Span currentSpan);


}
