package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.Span;
import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;

/**
 * default
 *
 * @author
 */
public class DefaultTrace implements Trace {

    private Span span;

    /**
     * span事件记录者
     *
     * @author 江浩
     */
    private SpanEventRecorder spanEventRecorder;

    public DefaultTrace(Span span, SpanEventRecorder spanEventRecorder) {
        this.span = span;
        this.spanEventRecorder = spanEventRecorder;
    }

    @Override
    public SpanEventRecorder currentSpanEventRecorder() {
        return this.spanEventRecorder;
    }

    @Override
    public String toString() {
        return "\t traceId:\t" + span.getTraceId().id()
                + "\t spanParentId:\t" + span.getParentId()
                + "\t spanId:\t" + span.getId()
                + "\t spanThreadId:\t" + span.getThreadId();
    }

}
