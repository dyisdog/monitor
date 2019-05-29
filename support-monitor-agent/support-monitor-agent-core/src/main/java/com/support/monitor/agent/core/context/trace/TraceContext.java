package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.recorder.TraceRootRecorder;

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
     * @param traceRootRecorder :
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace newTraceObject(TraceRootRecorder traceRootRecorder);

    /**
     * 新建
     *
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace newTraceObject();

    /**
     * 获取或者创建traceObject信息
     *
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace getOrNewTraceObject();

}