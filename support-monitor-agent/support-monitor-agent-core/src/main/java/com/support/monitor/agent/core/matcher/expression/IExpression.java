package com.support.monitor.agent.core.matcher.expression;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 语义解释
 *
 * @author 江浩
 */
public interface IExpression {

    /**
     * 语义解析
     * .cn <br>
     * org. <br>
     * .cn. <br>
     * com.example <br>
     *
     * @param content :
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<NamedElement>
     * @author 江浩
     */
    ElementMatcher.Junction<NamedElement> expression(String content);


}
