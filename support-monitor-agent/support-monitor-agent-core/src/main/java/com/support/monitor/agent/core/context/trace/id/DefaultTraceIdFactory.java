package com.support.monitor.agent.core.context.trace.id;

import com.support.monitor.agent.core.context.trace.DefaultDepth;

/**
 * default
 *
 * @author 江浩
 */
public class DefaultTraceIdFactory implements TraceIdFactory {
    @Override
    public TraceId newTraceId(String transactionId) {
        return this.newTraceId(transactionId, transactionId);
    }

    @Override
    public TraceId newTraceId(String transactionId, String parentSpanId) {
        // setting Depth
        return new DefaultTraceId(transactionId, parentSpanId, new DefaultDepth());
    }
}
