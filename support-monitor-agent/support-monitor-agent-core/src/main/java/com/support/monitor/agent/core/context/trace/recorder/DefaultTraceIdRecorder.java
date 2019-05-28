package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.id.TraceId;

/**
 * @author 江浩
 */
public class DefaultTraceIdRecorder implements TraceIdRecorder {

    private volatile TraceId traceId;

    public DefaultTraceIdRecorder(TraceId traceId) {
        this.traceId = traceId;
    }

    @Override
    public TraceId getTraceId() {
        return this.traceId;
    }

    @Override
    public long getCreateTime() {
        return this.traceId.getStartTime();
    }
}
