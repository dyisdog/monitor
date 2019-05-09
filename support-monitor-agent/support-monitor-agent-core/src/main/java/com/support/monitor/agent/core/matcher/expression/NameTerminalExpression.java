package com.support.monitor.agent.core.matcher.expression;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 根据名称解析
 * <p>
 * 1.content 一定是一个正则表达式
 * </p>
 *
 * @author 江浩
 */
public class NameTerminalExpression implements IExpression {

    @Override
    public ElementMatcher.Junction<NamedElement> expression(String content) {
        return ElementMatchers.nameMatches(content);
    }


}
