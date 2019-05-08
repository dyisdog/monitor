package com.support.monitor.agent.core.matcher.expression;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author 江浩
 */
@Data
@Builder
public class ExpressionContext {

    private Set<String> content;

}
