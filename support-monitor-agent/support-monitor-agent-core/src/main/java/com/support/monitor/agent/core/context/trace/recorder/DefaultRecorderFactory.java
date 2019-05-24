package com.support.monitor.agent.core.context.trace.recorder;

import com.google.inject.Inject;
import com.support.monitor.agent.core.context.trace.Span;

/**
 * @author 江浩
 */
public class DefaultRecorderFactory implements RecorderFactory {

    @Inject
    public DefaultRecorderFactory() {
    }

    @Override
    public SpanEventRecorder newSpanEventRecorder(Span span) {
        return new DefaultSpanEventRecorder(span);
    }
}
