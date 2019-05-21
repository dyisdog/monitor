package com.support.monitor.agent.core.context.trace;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class Trace {
    private String traceId;

    private String id;

    private String parentId;

    private Long startTime;

    private Long endTime;

    private Boolean isRoot = Boolean.FALSE;

    private AtomicLong step = new AtomicLong(1);

    public Trace(Trace parentTrace) {
        String tmp = UUID.randomUUID().toString().replaceAll("-", "");
        setTraceId(tmp);
        setId(tmp);
        setParentId(tmp);
        if (!Objects.isNull(parentTrace)) {
            setTraceId(parentTrace.getTraceId());
            setParentId(parentTrace.getId());
            AtomicLong step = parentTrace.getStep();
            step.incrementAndGet();
            setStep(step);
        }

        if (Objects.isNull(this.parentId) || StringUtils.equalsIgnoreCase(this.parentId, this.id)) {
            setIsRoot(true);
        }

    }


    public void traceBlockBegin() {
        setStartTime(System.currentTimeMillis());
    }

    public void traceBlockEnd() {
        setEndTime(System.currentTimeMillis());
    }
}
