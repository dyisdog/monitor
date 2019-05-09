package com.support.monitor.agent.core.matcher;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 默认的匹配器加载器
 *
 * @author 江浩
 */
public class DefaultTypeJunctionLoader implements IJunctionLoader {

    @Override
    public ElementMatcher.Junction<TypeDescription> ignoreJunction() {
        return ElementMatchers.any();
    }

    @Override
    public ElementMatcher.Junction<TypeDescription> typeJunction() {
        return ElementMatchers.any();
    }

    @Override
    public ElementMatcher.Junction<MethodDescription> methodJunction() {
        return ElementMatchers.any();
    }
}
