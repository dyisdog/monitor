package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.context.trace.recorder.TraceIdRecorder;
import com.support.monitor.agent.core.context.trace.span.Span;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;

/**
 * 追踪信息
 *
 * @author
 */
public interface Trace {

    /**
     * 当前trace的span 记录者
     *
     * @return : com.support.monitor.agent.core.context.trace.SpanEventRecorder
     * @author 江浩
     */
    TraceIdRecorder currentTraceIdRecorder();

    /**
     * 记录当前的span event事件信息
     *
     * @return : com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder
     * @author 江浩
     */
    SpanEventRecorder currentSpanEventRecorder();

    /**
     * 记录开始时间
     *
     * @param spanEvent
     * @return : void
     * @author 江浩
     */
    void traceBegin(SpanEvent spanEvent);

    /**
     * 记录结束信息
     *
     * @return : void
     * @author 江浩
     */
    Span traceEnd();


}
