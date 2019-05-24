package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.Span;
import com.support.monitor.agent.core.context.trace.TraceId;

/**
 * span 事件发送
 *
 * @author 江浩
 */
public interface SpanEventRecorder {

    /**
     * 从事件记录器中拿到traceId
     *
     * @return
     */
    TraceId getTraceId();


    /**
     * 是否异步事件
     *
     * @return : boolean
     * @author 江浩
     */
    boolean isAsync();

    /**
     * 获取span信息
     *
     * @return
     */
    Span getSpan();
}
