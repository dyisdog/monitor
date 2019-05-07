package com.support.monitor.agent.core.matcher.matcher;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 匹配器
 * @author 江浩
 */
public interface IMatcher<R> {

    /**
     * 构建匹配器
     * @return : net.bytebuddy.matcher.ElementMatcher<? super R>
     * @author 江浩
     */
    ElementMatcher<? super R> matcher();
}
