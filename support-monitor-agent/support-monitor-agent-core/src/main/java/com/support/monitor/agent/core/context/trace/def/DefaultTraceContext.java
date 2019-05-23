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
    public Trace continueTraceObject(TraceId traceId) {
        return traceFactory.continueAsyncTraceObject(traceId);
    }

    @Override
    public Trace continueTraceObject(Trace trace) {
        return traceFactory.continueTraceObject(trace);
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
    public Trace continueAsyncTraceObject(TraceId traceId) {
        return traceFactory.continueTraceObject(traceId);
    }
}
