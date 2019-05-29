package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.id.TraceId;

/**
 * traceRoot记录器，记录traceId 以及当前span
 *
 * @author 江浩
 */
public interface TraceRootRecorder {

    /**
     * 记录traceId
     *
     * @return : com.support.monitor.agent.core.context.trace.id.TraceId
     * @author 江浩
     */
    TraceId getTraceId();

    SpanEventRecorder currentSpanEventRecorder();


    /**
     * 获取traceId 创建时间
     *
     * @return : long
     * @author 江浩
     */
    long getCreateTime();
}
