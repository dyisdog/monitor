package com.support.monitor.agent.core.context;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.Objects;

public class DefaultActiveTracerSpan implements ActiveTracerSpan {

    private LinkedList<SofaTracerSpan> tracerSpans = Lists.newLinkedList();

    @Override
    public void addLast(SofaTracerSpan sofaTracerSpan) {
        if (Objects.isNull(sofaTracerSpan)) {
            return;
        }
        tracerSpans.addLast(sofaTracerSpan);
    }

    @Override
    public Integer size() {
        return tracerSpans.size();
    }

    @Override
    public SofaTracerSpan getLast() {
        return tracerSpans.getLast();
    }

    @Override
    public SofaTracerSpan stopLast() {
        return tracerSpans.removeLast();
    }
}
