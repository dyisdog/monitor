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


    ElementMatcher.Junction<TypeDescription> ignoreJunction();


    ElementMatcher.Junction<TypeDescription> typeJunction();


    ElementMatcher.Junction<MethodDescription> methodJunction();

}
