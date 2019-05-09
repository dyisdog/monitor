package com.support.monitor.agent.core.matcher.expression;

import lombok.Builder;
import lombok.Data;

import java.util.Stack;

@Data
@Builder
public class ExpressionContext {

    @Builder.Default
    private Stack<String> stack = new Stack<>();
}
