package com.support.monitor.agent.core.matcher;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 匹配器
 *
 * @author 江浩
 */
public interface IJunctionLoader {

    /**
     * 过滤匹配
     *
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<? super net.bytebuddy.description.type.TypeDescription>
     * @author 江浩
     */
    ElementMatcher.Junction<? super TypeDescription> ignoreJunction();

    /**
     * 类型匹配
     *
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<? super net.bytebuddy.description.type.TypeDescription>
     * @author 江浩
     */
    ElementMatcher.Junction<? super TypeDescription> typeJunction();

    /**
     * 方法匹配
     *
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<? super net.bytebuddy.description.method.MethodDescription>
     * @author 江浩
     */

    ElementMatcher.Junction<? super MethodDescription> methodJunction();

}
