package com.support.monitor.agent.core.context.trace.span;

import com.support.monitor.agent.core.context.trace.id.TraceId;
import com.support.monitor.commons.binder.utils.Assert;
import lombok.Data;

/**
 * 每一个方法调用的数据节点
 *
 * @author 江浩
 */
@Data
public class Span {

    private TraceId traceId;

    private String id;

    private String parentId;

    private long startTime;

    private long endTime;

    private long threadId;

    private boolean isAsync;

    public Span(String id, TraceId traceId) {
        Assert.requireNonNull(id, "id must not be null");
        Assert.requireNonNull(traceId, "traceId must not be null");
        this.id = id;
        this.traceId = traceId;
        this.setParentId(traceId.parentSpanId());
        this.setThreadId(Thread.currentThread().getId());
    }

    public void markBeforeTime() {
        setStartTime(System.currentTimeMillis());
    }


    public long executeTime() {
        return endTime - startTime;
    }
}
