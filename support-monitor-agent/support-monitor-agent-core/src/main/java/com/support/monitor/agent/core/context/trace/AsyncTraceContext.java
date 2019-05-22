package com.support.monitor.agent.core.context.trace;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author
 */
public interface AsyncTraceContext {

    /**
     * 当前线程相关Trace
     *
     * @return : com.support.monitor.agent.core.context.Trace
     * @author 江浩
     */
    Trace currentTraceObject();

    /**
     * 异步线程创建Trace
     *
     * @param traceId :
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace newAsyncTraceObject(TraceId traceId);

    /**
     * ref
     *
     * @return
     */
    AtomicReference<Trace> getReferenceTrace();

    /**
     * close remove currentTraceObject
     */
    void close();

}
