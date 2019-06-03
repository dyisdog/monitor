package com.support.monitor.agent.core.context;

import com.alipay.common.tracer.core.span.SofaTracerSpan;

public class DefaultReporter implements Reporter {
    @Override
    public String getReporterType() {
        return null;
    }

    @Override
    public void report(SofaTracerSpan sofaTracerSpan) {
        System.out.println(sofaTracerSpan);
        System.out.println("threadId: " + Thread.currentThread().getId()
                + "\t traceId: " + sofaTracerSpan.getSofaTracerSpanContext().getTraceId()
                + "\t preSpanId: " + sofaTracerSpan.getSofaTracerSpanContext().getParentId()
                + "\t spanId: " + sofaTracerSpan.getSofaTracerSpanContext().getSpanId()
                + "\t startTime: " + sofaTracerSpan.getStartTime()
                + "\t endTime：" + sofaTracerSpan.getEndTime()
        );

    }

    @Override
    public void close() {

    }
}
