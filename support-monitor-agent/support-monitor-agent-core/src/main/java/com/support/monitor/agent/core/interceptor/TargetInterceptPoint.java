package com.support.monitor.agent.core.interceptor;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 拦截点
 *
 * @author
 */
public interface TargetInterceptPoint {
    /**
     * 类型匹配
     *
     * @return
     */
    ElementMatcher<? super TypeDescription> classMatcher();

    /**
     * 方法匹配
     *
     * @return
     */
    ElementMatcher<? super MethodDescription> methodMatcher();
}
