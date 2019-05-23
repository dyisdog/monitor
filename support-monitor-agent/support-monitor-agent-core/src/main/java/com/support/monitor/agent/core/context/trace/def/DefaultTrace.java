package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.Span;
import com.support.monitor.agent.core.context.trace.SpanEventRecorder;
import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceId;

/**
 * default
 *
 * @author
 */
public class DefaultTrace implements Trace {

    private Span span;

    public DefaultTrace(Span span) {
        this.span = span;
    }

    @Override
    public TraceId getTraceId() {
        return this.span.getTraceId();
    }

    @Override
    public SpanEventRecorder currentSpanEventRecorder() {
        return null;
    }


    @Override
    public String toString() {
        return "\t traceId:\t" + span.getTraceId().id()
                + "\t spanParentId:\t" + span.getParentId()
                + "\t spanId:\t" + span.getId()
                + "\t spanThreadId:\t" + span.getThreadId();
    }

    @Override
    public SpanEventRecorder traceBlockBegin() {
        return traceBlockBegin(DEFAULT_STACKID);
    }

    @Override
    public SpanEventRecorder traceBlockBegin(int stackId) {
        return null;
    }

    @Override
    public void traceBlockEnd() {

    }

    @Override
    public void traceBlockEnd(int stackId) {

    }

    @Override
    public boolean isRootStack() {
        return false;
    }

    @Override
    public int getCallStackFrameId() {
        return 0;
    }

//
//    private SpanEventRecorder wrappedSpanEventRecorder(WrappedSpanEventRecorder wrappedSpanEventRecorder, SpanEvent spanEvent) {
//        wrappedSpanEventRecorder.setWrapped(spanEvent);
//        return wrappedSpanEventRecorder;
//    }
//
//    private SpanEvent newSpanEvent(int stackId) {
//        final SpanEvent spanEvent = callStack.getFactory().newInstance();
//
//        spanEvent.markStartTime();
//        spanEvent.setStackId(stackId);
//
//        return spanEvent;
//    }
}
