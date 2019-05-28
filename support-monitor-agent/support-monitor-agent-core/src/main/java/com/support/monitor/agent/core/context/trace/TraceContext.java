package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.id.TraceId;

/**
 * TraceContext
 *
 * @author
 */
public interface TraceContext {


    /**
     * 当前trace object
     *
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace currentRawTraceObject();

    /**
     * 根据traceId创建新的Trace
     *
     * @param traceId :
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace newTraceObject(TraceId traceId);

    /**
     * 新建
     *
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace newTraceObject();

}