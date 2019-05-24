package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;

/**
 * 追踪信息
 *
 * @author
 */
public interface Trace {

    /**
     * 当前trace的span 记录者
     *
     * @return : com.support.monitor.agent.core.context.trace.SpanEventRecorder
     * @author 江浩
     */
    SpanEventRecorder currentSpanEventRecorder();

}
