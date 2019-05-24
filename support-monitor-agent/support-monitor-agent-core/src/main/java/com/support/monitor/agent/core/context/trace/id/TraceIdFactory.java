package com.support.monitor.agent.core.context.trace.id;

/**
 * TraceId 构建工厂
 *
 * @author 江浩
 */
public interface TraceIdFactory {

    /**
     * 创建TraceId
     *
     * @param transactionId
     * @return
     */
    TraceId newTraceId(String transactionId);

    /**
     * 设置上一次span的id信息
     *
     * @param parentSpanId
     * @param transactionId
     * @return : com.support.monitor.agent.core.context.trace.TraceId
     * @author 江浩
     */
    TraceId newTraceId(String transactionId, String parentSpanId);
}
