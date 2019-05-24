package com.support.monitor.agent.core.context.trace.recorder;

import com.support.monitor.agent.core.context.trace.Span;

/**
 * 记录者构建工厂
 * <p>
 * 为某个对象信息构建记录备忘信息
 * </P>
 *
 * @author 江浩
 */
public interface RecorderFactory {

    /**
     * span 信息记录者构建
     *
     * @return : com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder
     * @author 江浩
     */
    SpanEventRecorder newSpanEventRecorder(Span span);

}
