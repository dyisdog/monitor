package com.support.monitor.agent.core.matcher.matcher;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 方法匹配器
 * <br>
 * 匹配所有方法
 *
 * @author 江浩
 */
public class MethodMatcher implements IMatcher<MethodDescription> {
    @Override
    public ElementMatcher<? super MethodDescription> matcher() {
        return ElementMatchers.any();
    }
}
