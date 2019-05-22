package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.TraceId;
import com.support.monitor.agent.core.context.trace.TraceIdFactory;

/**
 * default
 *
 * @author 江浩
 */
public class DefaultTraceIdFactory implements TraceIdFactory {
    @Override
    public TraceId newTraceId(String transactionId) {
        return this.newTraceId(transactionId, null);
    }

    @Override
    public TraceId newTraceId(String transactionId, String parentSpanId) {
        return new DefaultTraceId(transactionId, parentSpanId);
    }
}
