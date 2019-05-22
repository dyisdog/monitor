package com.support.monitor.agent.core.context.trace;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Trace 管理工厂
 *
 * @author 江浩
 */
public interface TraceFactory {

    /**
     * 获取当前线程的Trace
     *
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace currentTraceObject();

    /**
     * 删除线程绑定的trace
     *
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace removeTraceObject();

    /**
     * 创建Trace
     *
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace newTraceObject();

    /**
     * 根据TraceId 创建
     *
     * @param traceId :
     * @return : com.support.monitor.agent.core.context.trace.Trace
     * @author 江浩
     */
    Trace newTraceObject(TraceId traceId);

    /**
     * 获取映射绑定
     *
     * @return
     */
    AtomicReference<Trace> getReference();

}
