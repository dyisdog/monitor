package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.AsyncTraceContext;
import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceFactory;
import com.support.monitor.agent.core.context.trace.TraceId;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * TraceContext 默认实现
 *
 * @author 江浩
 */
@Slf4j
public class DefaultAsyncTraceContext implements AsyncTraceContext {

    private TraceFactory traceFactory;

    public DefaultAsyncTraceContext(TraceFactory traceFactory) {
        this.traceFactory = traceFactory;
    }

    @Override
    public Trace currentTraceObject() {
        return traceFactory.currentTraceObject();
    }

    @Override
    public Trace newAsyncTraceObject(TraceId traceId) {
        return traceFactory.newTraceObject(traceId);
    }

    @Override
    public AtomicReference<Trace> getReferenceTrace() {
        return traceFactory.getReference();
    }

    @Override
    public void close() {
        traceFactory.removeTraceObject();
    }
}
