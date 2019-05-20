package com.support.monitor.agent.core.context.trace;

import lombok.Builder;
import lombok.Data;

/**
 * span
 *
 * @author 江浩
 */
@Data
@Builder
public class Span {

    private String traceId;

    private String spanId;

    private String parentSpanId;

    private Long startTime;

    private Long endTime;

    @Builder.Default
    private Boolean isRoot = Boolean.TRUE;

}
