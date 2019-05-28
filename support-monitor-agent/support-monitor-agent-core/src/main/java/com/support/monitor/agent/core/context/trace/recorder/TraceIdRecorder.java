package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.id.TraceId;

/**
 * traceIdRecorder 该记录器，会记录当前trace 的ID信息，一个trace信息中只会存在一个traceId，traceId中的id信息不能变化
 *
 * @author 江浩
 */
public interface TraceIdRecorder {

    /**
     * 记录traceId
     *
     * @return : com.support.monitor.agent.core.context.trace.id.TraceId
     * @author 江浩
     */
    TraceId getTraceId();

    /**
     * 获取traceId 创建时间
     *
     * @return : long
     * @author 江浩
     */
    long getCreateTime();
}
