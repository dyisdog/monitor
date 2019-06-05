package com.support.monitor.agent.core.context;

import com.alipay.common.tracer.core.span.SofaTracerSpan;

/**
 * 默认的上报器
 * <p>以插件的方式加载传输器</p>
 *
 * @author 江浩
 */
public class DefaultReporter implements Reporter {
    @Override
    public String getReporterType() {
        return null;
    }

    @Override
    public void report(SofaTracerSpan sofaTracerSpan) {
        System.out.println("threadId: " + Thread.currentThread().getId()
                + "\t traceId: " + sofaTracerSpan.getSofaTracerSpanContext().getTraceId()
                + "\t preSpanId: " + sofaTracerSpan.getSofaTracerSpanContext().getParentId()
                + "\t spanId: " + sofaTracerSpan.getSofaTracerSpanContext().getSpanId()
                + "\t startTime: " + sofaTracerSpan.getStartTime()
                + "\t endTime：" + sofaTracerSpan.getEndTime()
                + "\n \t\t" + sofaTracerSpan.getBaggageItem(TraceContext.STOP_OBJECT_KEY)
        );

    }

    @Override
    public void close() {

    }
}
