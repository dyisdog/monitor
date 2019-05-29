package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.agent.core.context.trace.span.Span;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;
import com.support.monitor.agent.core.context.trace.span.SpanFactory;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Objects;

/**
 * <p>
 * 每一个trace携带的recorder不一样
 * 对于同一个spanEventRecorder实例 主线程中使用多个子线程？并发
 * </p>
 *
 * @author 江浩
 */
@Getter
public class DefaultSpanEventRecorder extends ArrayDeque<Span> implements SpanEventRecorder {

    private SpanFactory spanFactory;

    private volatile Span currentSpan;

    public DefaultSpanEventRecorder(SpanFactory spanFactory) {
        this.spanFactory = spanFactory;
    }

    @Override
    public void spanEventBegin(SpanEvent spanEvent, TraceId traceId) {
        Span span = spanFactory.newSpan(traceId, spanEvent, this.currentSpan);
        span.markBeforeTime();
        addLast(span);

        this.currentSpan = span;
    }

    @Override
    public Span spanEventEnd(TraceId traceId) {
        //应该使用poll删除传送
        Span span = pollFirst();
        if (!Objects.isNull(span)) {
            span.markAfterTime();
        }
        return span;
    }

    @Override
    public Span currentSpan() {
        return this.currentSpan;
    }

    @Override
    public void resetCurrentSpan(Span currentSpan) {
        this.currentSpan = currentSpan;
    }


}
