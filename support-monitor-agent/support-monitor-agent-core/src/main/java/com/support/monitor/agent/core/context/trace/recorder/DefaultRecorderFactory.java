package com.support.monitor.agent.core.context.trace.recorder;

import com.google.inject.Inject;
import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.agent.core.context.trace.span.SpanFactory;

/**
 * @author 江浩
 */
public class DefaultRecorderFactory implements RecorderFactory {

    private SpanFactory spanFactory;

    @Inject
    public DefaultRecorderFactory(SpanFactory spanFactory) {
        this.spanFactory = spanFactory;
    }

    @Override
    public SpanEventRecorder newSpanEventRecorder() {
        return new DefaultSpanEventRecorder(spanFactory);
    }

    @Override
    public TraceRootRecorder newTraceRootRecorder(TraceId traceId, SpanEventRecorder spanEventRecorder) {
        return new DefaultTraceRootRecorder(traceId, spanEventRecorder);
    }
}
