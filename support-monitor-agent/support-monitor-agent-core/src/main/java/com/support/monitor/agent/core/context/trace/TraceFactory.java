package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.id.TraceId;

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

    /**
     * 创建
     *
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace newTraceObject();

    /**
     * 删除
     *
     * @return
     */
    Trace removeTraceObject();

    /**
     * 创建
     *
     * @param traceId
     * @return
     */
    Trace newTraceObject(TraceId traceId);


    AtomicReference<Trace> getReference();

}
