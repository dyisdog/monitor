package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.agent.core.context.trace.span.Span;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;
import com.support.monitor.agent.core.context.trace.span.SpanFactory;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>
 * 每一个trace携带的recorder不一样
 * 对于同一个spanEventRecorder实例 主线程中使用多个子线程？并发
 * </p>
 *
 * @author 江浩
 */
public class DefaultSpanEventRecorder extends ConcurrentLinkedQueue<Span> implements SpanEventRecorder {

    private SpanFactory spanFactory;
    //TODO sender ?

    public DefaultSpanEventRecorder(SpanFactory spanFactory) {
        this.spanFactory = spanFactory;
    }

    @Override
    public void startSpanEventBlock(SpanEvent spanEvent, TraceId traceId) {
        Span span = spanFactory.newSpan(traceId, spanEvent);
        span.markBeforeTime();
        add(span);
    }

    @Override
    public Span endSpanEventBlock(TraceId traceId) {
        Span span = peek();
        if (!Objects.isNull(span)) {
            span.markAfterTime();
        }
        return span;
    }

    @Override
    public Span getFirstSpanEvent() {
        Span span = poll();
        //System.out.println(span.getSpanEvent().getEventTarget() + "  " + span.getSpanEvent().getEventMethod() + "  " + span.getDepth() + "  " + span.getTraceId());
        return span;
    }


}
