package com.support.monitor.agent.core.context.trace;

import com.support.monitor.commons.binder.Binder;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TraceContext 默认实现
 *
 * @author 江浩
 */
public class DefaultTraceContext implements TraceContext {

    private Binder<Trace> binder;

    //TODO 使用traceFactory构建
    public DefaultTraceContext(Binder<Trace> binder) {
        this.binder = binder;
    }

    private void bind(AtomicReference<Trace> traceAtomicReference, Trace trace) {
        traceAtomicReference.set(trace);
    }

    @Override
    public Trace currentTraceObject() {
        final AtomicReference<Trace> traceAtomicReference = this.check();
        return traceAtomicReference.get();
    }

    private AtomicReference<Trace> check() {
        return binder.get();
    }

    @Override
    public Trace newTraceObject() {
        AtomicReference<Trace> traceAtomicReference = this.check();
        Trace trace = traceAtomicReference.get();
        if (Objects.isNull(trace)) {
            trace = new Trace(null);
        } else {
            trace = new Trace(trace);
        }
        this.bind(traceAtomicReference, trace);
        return trace;
    }

}
