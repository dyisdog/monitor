package com.support.monitor.agent.core.context.trace;

/**
 * 传递的ID
 *
 * @author 江浩
 */
public interface TraceId {

    /**
     * 唯一标识ID
     *
     * @return
     */
    String id();

    /**
     * 上一个执行的spanId
     *
     * @return
     */
    String parentSpanId();

    /**
     * 链路执行的开始时间
     *
     * @return : long
     * @author 江浩
     */
    long getStartTime();

}
