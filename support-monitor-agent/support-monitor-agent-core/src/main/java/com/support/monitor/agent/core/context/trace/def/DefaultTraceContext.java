package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.*;

import java.util.concurrent.atomic.AtomicReference;

/**
 * TraceContext 默认实现
 *
 * @author 江浩
 */
public class DefaultTraceContext implements TraceContext {

    private TraceFactory traceFactory;

    private AsyncTraceContext asyncTraceContext;

    public DefaultTraceContext(TraceFactory traceFactory, AsyncTraceContext asyncTraceContext) {
        this.traceFactory = traceFactory;
        this.asyncTraceContext = asyncTraceContext;
    }

    @Override
    public Trace currentTraceObject() {
        return traceFactory.currentTraceObject();
    }

    @Override
    public Trace newTraceObject() {
        return traceFactory.newTraceObject();
    }

    @Override
    public Trace newTraceObject(TraceId traceId) {
        return traceFactory.newTraceObject(traceId);
    }

    @Override
    public AtomicReference<Trace> getReference() {
        return traceFactory.getReference();
    }

    @Override
    public Trace nextAsyncTrace(TraceId traceId) {
        return asyncTraceContext.newAsyncTraceObject(traceId);
    }


}
