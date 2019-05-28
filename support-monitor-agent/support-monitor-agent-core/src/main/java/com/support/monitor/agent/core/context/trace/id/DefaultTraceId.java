package com.support.monitor.agent.core.context.trace.id;

import com.support.monitor.agent.core.context.trace.Depth;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author
 */
@Getter
public class DefaultTraceId implements TraceId {

    private final String id;

    private AtomicReference<String> atomicReference = new AtomicReference<>();

    private final long startTime = System.currentTimeMillis();

    private final Depth depth;


    public DefaultTraceId(String id, String lockedId, Depth depth) {
        this.id = id;
        atomicReference.set(lockedId);
        this.depth = depth;
    }

    @Override
    public String id() {
        return this.id;
    }


    @Override
    public Depth getDepth() {
        return depth;
    }

}
