package com.support.monitor.agent.core.context;

import com.alipay.common.tracer.core.SofaTracer;
import com.alipay.common.tracer.core.context.trace.SofaTracerThreadLocalTraceContext;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.commons.binder.NamedThreadLocal;
import lombok.Getter;

import java.util.EmptyStackException;
import java.util.Objects;

/**
 * 默认的追踪上线文
 *
 * @author 江浩
 */
@Getter
public class DefaultTraceContext extends SofaTracerThreadLocalTraceContext implements TraceContext {

    private SofaTracer sofaTracer;

    /**
     * 当前线程中活跃的span信息
     *
     * @author 江浩
     */
    private final NamedThreadLocal<ActiveTracerSpan> activeTracerSpans = new NamedThreadLocal<>("activeTracerSpans");

    public DefaultTraceContext(SofaTracer sofaTracer) {
        this.sofaTracer = sofaTracer;
    }

    @Override
    public SofaTracer getSofaTracer() {
        return this.sofaTracer;
    }

    @Override
    public SofaTracerSpan stopCurrentTracerSpan() {
        SofaTracerSpan sofaTracerSpan = this.pop();
        if (!Objects.isNull(sofaTracerSpan)) {
            sofaTracerSpan.finish();
        }
        return sofaTracerSpan;
    }


    @Override
    public void push(SofaTracerSpan span) {
        if (span == null) {
            return;
        }
        //当前线程
        ActiveTracerSpan activeTracerSpan = activeTracerSpans.get();
        if (Objects.isNull(activeTracerSpan)) {
            activeTracerSpan = new DefaultActiveTracerSpan();
            activeTracerSpans.set(activeTracerSpan);
        }
        activeTracerSpan.addLast(span);
    }

    @Override
    public SofaTracerSpan getCurrentSpan() throws EmptyStackException {
        if (this.isEmpty()) {
            return null;
        }

        ActiveTracerSpan activeTracerSpan = activeTracerSpans.get();
        if (Objects.isNull(activeTracerSpan)) {
            return null;
        }

        return activeTracerSpan.getLast();
    }

    @Override
    public SofaTracerSpan pop() throws EmptyStackException {
        if (this.isEmpty()) {
            return null;
        }
        ActiveTracerSpan activeTracerSpan = activeTracerSpans.get();
        if (Objects.isNull(activeTracerSpan)) {
            return null;
        }

        SofaTracerSpan sofaTracerSpan = activeTracerSpan.stopLast();
        if (activeTracerSpan.size() <= 0) {
            activeTracerSpans.remove();
        }
        return sofaTracerSpan;
    }

    @Override
    public int getThreadLocalSpanSize() {
        ActiveTracerSpan activeTracerSpan = activeTracerSpans.get();
        return activeTracerSpan == null ? 0 : activeTracerSpan.size();
    }

    @Override
    public boolean isEmpty() {
        ActiveTracerSpan activeTracerSpan = activeTracerSpans.get();
        return activeTracerSpan == null || activeTracerSpan.size() <= 0;
    }

    @Override
    public void clear() {
//        ActiveTracerSpan activeTracerSpan = activeTracerSpans.get();
        activeTracerSpans.remove();
    }


}
