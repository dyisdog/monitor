package com.support.monitor.agent.core.matcher.expression;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.commons.lang3.StringUtils;

/**
 * 根据名称解析
 * <p>
 * 1.该类解析分解过后的参数 .cn.|.com=> .cn.  .com
 * </p>
 *
 * @author 江浩
 */
public class NameTerminalExpression implements IExpression {

    private static final String TAG = ".";

    @Override
    public ElementMatcher.Junction<NamedElement> expression(String content) {

        //if empty
        if (StringUtils.isBlank(content)) {
            return ElementMatchers.any();
        }
        //.cn.
        if (StringUtils.startsWithIgnoreCase(content, TAG) && StringUtils.endsWithIgnoreCase(content, TAG)) {
            return ElementMatchers.nameContainsIgnoreCase(subContent(content));
        }
        //.cn
        if (StringUtils.startsWithIgnoreCase(content, TAG) && !StringUtils.endsWithIgnoreCase(content, TAG)) {
            return ElementMatchers.nameEndsWithIgnoreCase(subContent(content));
        }
        //cn.
        if (!StringUtils.startsWithIgnoreCase(content, TAG) && StringUtils.endsWithIgnoreCase(content, TAG)) {
            return ElementMatchers.nameStartsWithIgnoreCase(subContent(content));
        }
        //cn
        return ElementMatchers.named(content);
    }

    private String subContent(String content) {
        if (StringUtils.startsWithIgnoreCase(content, TAG)) {
            content = StringUtils.substringAfter(content, TAG);
        }
        if (StringUtils.endsWithIgnoreCase(content, TAG)) {
            content = StringUtils.substringBeforeLast(content, TAG);
        }
        return content;
    }


}
