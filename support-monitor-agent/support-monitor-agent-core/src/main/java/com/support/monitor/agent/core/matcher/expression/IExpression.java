package com.support.monitor.agent.core.matcher.expression;

import net.bytebuddy.matcher.ElementMatcher;

/**
 * 语义解释
 *
 * @author 江浩
 */
public interface IExpression<R> {

    /**
     * 语义解析
     *
     * @param content :
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<? super net.bytebuddy.description.type.TypeDescription>
     * @author 江浩
     */
    ElementMatcher.Junction<? super R> expression(String content);

}
