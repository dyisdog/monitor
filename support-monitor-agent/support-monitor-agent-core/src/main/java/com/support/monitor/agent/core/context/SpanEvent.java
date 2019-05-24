package com.support.monitor.agent.core.context;

import com.support.monitor.agent.core.context.trace.Span;
import com.support.monitor.agent.core.context.trace.TraceId;
import lombok.Getter;

import java.util.Objects;

/**
 * 事件信息记录
 *
 * @author 江浩
 */
@Getter
public class SpanEvent {

    private TraceId traceId;
    private Span span;
    private long startTime;
    private long endTime;

    private boolean isAsync;

    //请求信息 TODO

    public SpanEvent(Span span) {
        this.span = span;
        this.traceId = Objects.isNull(span) ? null : span.getTraceId();
    }


}
