package com.support.monitor.agent.core.context.trace.span;

import lombok.Builder;
import lombok.Data;

/**
 * @author 江浩
 */
@Builder
@Data
public class SpanEvent {

    private String eventTarget;

    private String eventMethod;

}
