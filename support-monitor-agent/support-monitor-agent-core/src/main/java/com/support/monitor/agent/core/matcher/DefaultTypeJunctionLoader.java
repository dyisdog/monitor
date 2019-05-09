package com.support.monitor.agent.core.matcher;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 默认的匹配器加载器
 *
 * @author 江浩
 */
public class DefaultTypeJunctionLoader implements IJunctionLoader {


    @Override
    public ElementMatcher.Junction<NamedElement> ignoreJunction() {
        return ElementMatchers.any();
    }

    @Override
    public ElementMatcher.Junction<NamedElement> typeJunction() {
        return ElementMatchers.any();
    }


    @Override
    public ElementMatcher.Junction<NamedElement> methodJunction() {
        return ElementMatchers.any();
    }
}
