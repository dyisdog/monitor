package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import com.support.monitor.agent.core.context.trace.recorder.TraceRootRecorder;
import com.support.monitor.agent.core.context.trace.span.Span;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;

/**
 * default
 *
 * @author
 */
public class DefaultTrace implements Trace {

    /**
     * 链路恒定传递的值信息
     *
     * @author 江浩
     */
    private TraceRootRecorder traceRootRecorder;


    /**
     * 当前span事件记录器
     *
     * @return : com.support.monitor.agent.core.context.trace.span.Span
     * @author 江浩
     */
    private SpanEventRecorder spanEventRecorder;


    public DefaultTrace(TraceRootRecorder traceRootRecorder, SpanEventRecorder spanEventRecorder) {
        this.traceRootRecorder = traceRootRecorder;
        this.spanEventRecorder = spanEventRecorder;
    }


    @Override
    public TraceRootRecorder currentTraceRootRecorder() {
        return this.traceRootRecorder;
    }

    @Override
    public SpanEventRecorder currentSpanEventRecorder() {
        return this.spanEventRecorder;
    }

    @Override
    public SpanEventRecorder traceBegin(SpanEvent spanEvent) {
        TraceId traceId = traceRootRecorder.getTraceId();
        //TODO spanId
        spanEventRecorder.spanEventBegin(spanEvent, traceId);
        return spanEventRecorder;
    }

    @Override
    public Span traceEnd() {
        //同一个线程中包含同一个traceId
        return spanEventRecorder.spanEventEnd(traceRootRecorder.getTraceId());
    }

}
