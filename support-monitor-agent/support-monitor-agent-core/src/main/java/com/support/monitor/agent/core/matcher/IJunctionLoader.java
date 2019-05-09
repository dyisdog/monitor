package com.support.monitor.agent.core.matcher;

import net.bytebuddy.description.NamedElement;
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
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<net.bytebuddy.description.NamedElement>
     * @author 江浩
     */
    ElementMatcher.Junction<NamedElement> ignoreJunction();

    /**
     * 类型匹配
     *
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<net.bytebuddy.description.NamedElement>
     * @author 江浩
     */
    ElementMatcher.Junction<NamedElement> typeJunction();

    /**
     * 方法匹配
     *
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<net.bytebuddy.description.NamedElement>
     * @author 江浩
     */
    ElementMatcher.Junction<NamedElement> methodJunction();

}
