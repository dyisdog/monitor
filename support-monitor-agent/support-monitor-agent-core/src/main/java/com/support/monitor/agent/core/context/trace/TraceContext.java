package com.support.monitor.agent.core.context.trace;

/**
 * TraceContext
 *
 * @author
 */
public interface TraceContext {

    /**
     * 当前线程相关Trace
     *
     * @return : com.support.monitor.agent.core.context.Trace
     * @author 江浩
     */
    Trace currentTraceObject();

    Trace newTraceObject();
}
