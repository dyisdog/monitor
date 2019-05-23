package com.support.monitor.agent.core.context.trace;

/**
 * 追踪信息
 *
 * @author
 */
public interface Trace extends StackOperation {

    /**
     * 每个一个追踪信息渗透根据 {@link TraceId}
     *
     * @return
     */
    TraceId getTraceId();

    /**
     * 是否是异步执行创建
     *
     * @return : boolean
     * @author 江浩
     */
    default boolean isAsync() {
        return true;
    }

    /**
     * TODO
     *
     * @return : com.support.monitor.agent.core.context.trace.SpanEventRecorder
     * @author 江浩
     */
    SpanEventRecorder currentSpanEventRecorder();

}
