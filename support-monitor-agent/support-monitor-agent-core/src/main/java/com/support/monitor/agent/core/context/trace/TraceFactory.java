package com.support.monitor.agent.core.context.trace;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Trace 管理工厂
 *
 * @author 江浩
 */
public interface TraceFactory {

    /**
     * 返回当前线程绑定Trace 可能为空
     *
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace currentRawTraceObject();

    Trace removeTraceObject();


    Trace continueTraceObject(TraceId traceId);

    Trace continueTraceObject(Trace trace);

    Trace continueAsyncTraceObject(TraceId traceId);

    Trace newTraceObject();

    Trace newAsyncTraceObject();

    AtomicReference<Trace> getReference();

}
