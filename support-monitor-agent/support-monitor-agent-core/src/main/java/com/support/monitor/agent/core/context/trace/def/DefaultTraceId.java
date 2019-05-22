package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.TraceId;
import lombok.Getter;

/**
 * @author
 */
@Getter
public class DefaultTraceId implements TraceId {

    private final String id;

    private final String parentSpanId;

    private final long startTime = System.currentTimeMillis();

    public DefaultTraceId(String id, String parentSpanId) {
        this.id = id;
        this.parentSpanId = parentSpanId;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String parentSpanId() {
        return this.parentSpanId;
    }

}
