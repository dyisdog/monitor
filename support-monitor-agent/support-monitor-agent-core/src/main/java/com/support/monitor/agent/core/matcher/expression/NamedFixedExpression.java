package com.support.monitor.agent.core.matcher.expression;

import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.commons.lang3.StringUtils;

/**
 * 任意一个的处理方式
 *
 * @author 江浩
 */
public class NamedFixedExpression implements IExpression {

    private static final String FIXED = ".";

    //.cn.|.com&org.,com.example
    //.cn
    //org.
    //.cn.
    //com.example
    @Override
    public ElementMatcher.Junction expression(String content) {
        if (StringUtils.startsWithIgnoreCase(content, FIXED) && StringUtils.endsWithIgnoreCase(content, FIXED)) {
            return ElementMatchers.nameContainsIgnoreCase(content);
        } else if (StringUtils.startsWithIgnoreCase(content, FIXED) && !StringUtils.endsWithIgnoreCase(content, FIXED)) {
            return ElementMatchers.nameEndsWithIgnoreCase(content);
        } else if (!StringUtils.startsWithIgnoreCase(content, FIXED) && StringUtils.endsWithIgnoreCase(content, FIXED)) {
            return ElementMatchers.nameStartsWithIgnoreCase(content);
        } else {
            return ElementMatchers.named(content);
        }
    }
}
