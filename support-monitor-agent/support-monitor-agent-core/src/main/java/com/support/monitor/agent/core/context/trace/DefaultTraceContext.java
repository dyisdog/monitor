package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.recorder.TraceRootRecorder;

import java.util.Objects;

/**
 * TraceContext 默认实现
 *
 * @author 江浩
 */
public class DefaultTraceContext implements TraceContext {

    private TraceFactory traceFactory;

    public DefaultTraceContext(TraceFactory traceFactory) {
        this.traceFactory = traceFactory;
    }

    @Override
    public Trace currentRawTraceObject() {
        return traceFactory.currentRawTraceObject();
    }

    @Override
    public Trace newTraceObject(TraceRootRecorder traceRootRecorder) {
        return traceFactory.newTraceObject(traceRootRecorder);
    }

    @Override
    public Trace newTraceObject() {
        return traceFactory.newTraceObject();
    }

    @Override
    public Trace getOrNewTraceObject() {
        Trace trace = this.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            trace = this.newTraceObject();
        }
        return trace;
    }


}
