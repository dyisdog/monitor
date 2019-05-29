package com.support.monitor.agent.core.context.trace;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 默认traceId深度实现
 *
 * @author 江浩
 */
public class DefaultDepth implements Depth {

    private AtomicLong depth = new AtomicLong(0);

    @Override
    public Long nextDepth() {
        return depth.getAndIncrement();
    }

    @Override
    public Long getDepth() {
        return depth.get();
    }
}
