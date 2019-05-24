package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.TraceFactory;
import com.support.monitor.agent.core.context.trace.TraceId;

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
    public Trace newTraceObject(TraceId traceId) {
        return traceFactory.newAsyncTraceObject(traceId);
    }

    @Override
    public Trace newTraceObject(Trace trace) {
        return traceFactory.newTraceObject(trace);
    }

    @Override
    public Trace newTraceObject() {
        return traceFactory.newTraceObject();
    }

    @Override
    public Trace newAsyncTraceObject() {
        return traceFactory.newAsyncTraceObject();
    }

    @Override
    public Trace newAsyncTraceObject(TraceId traceId) {
        return traceFactory.newAsyncTraceObject(traceId);
    }
}
