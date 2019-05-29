package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.context.trace.recorder.TraceRootRecorder;
import com.support.monitor.agent.core.context.trace.span.Span;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;

/**
 * 追踪信息
 *
 * @author
 */
public interface Trace {

    /**
     * 当前线程 TraceId 记录者 链路追踪传递信息
     *
     * @return : com.support.monitor.agent.core.context.trace.SpanEventRecorder
     * @author 江浩
     */
    TraceRootRecorder currentTraceRootRecorder();

    /**
     * 当前线程对应的spanEvent 记录器
     *
     * @return : com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder
     * @author 江浩
     */
    SpanEventRecorder currentSpanEventRecorder();

    /**
     * 记录开始时间
     * <p>生成一个span记录器</p>
     *
     * @param spanEvent
     * @return : void
     * @author 江浩
     */
    SpanEventRecorder traceBegin(SpanEvent spanEvent);

    /**
     * 记录结束信息
     *
     * @return : void
     * @author 江浩
     */
    Span traceEnd();


}
