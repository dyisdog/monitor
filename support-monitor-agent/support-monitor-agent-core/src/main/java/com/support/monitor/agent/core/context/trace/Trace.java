package com.support.monitor.agent.core.context.trace;

/**
 * 追踪信息
 *
 * @author
 */
public interface Trace {

    /**
     * 每个一个追踪信息渗透根据 {@link TraceId}
     *
     * @return
     */
    TraceId getTraceId();

    /**
     * 获取SPAN信息
     *
     * @return
     */
    Span getSpan();

    /**
     * 是否是异步执行创建
     *
     * @return : boolean
     * @author 江浩
     */
    default boolean isAsync() {
        return true;
    }

}
