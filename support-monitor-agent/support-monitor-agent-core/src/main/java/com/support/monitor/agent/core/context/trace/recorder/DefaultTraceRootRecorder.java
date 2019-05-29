package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.id.TraceId;

/**
 * @author 江浩
 */
public class DefaultTraceRootRecorder implements TraceRootRecorder {

    private volatile TraceId traceId;
    private volatile SpanEventRecorder spanEventRecorder;

    public DefaultTraceRootRecorder(TraceId traceId, SpanEventRecorder spanEventRecorder) {
        this.traceId = traceId;
        this.spanEventRecorder = spanEventRecorder;
    }

    @Override
    public TraceId getTraceId() {
        return this.traceId;
    }

    @Override
    public SpanEventRecorder currentSpanEventRecorder() {
        return this.spanEventRecorder;
    }


    @Override
    public long getCreateTime() {
        return this.traceId.getStartTime();
    }
}
