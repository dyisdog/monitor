package com.support.monitor.agent.core.matcher;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 插件方式加载匹配器
 *
 * @author 江浩
 */
public class PluginJunctionLoader extends AbstractLoadJunctionLoader {
    public PluginJunctionLoader(IJunctionLoader junctionLoader) {
        super(junctionLoader);
    }


    @Override
    public ElementMatcher.Junction<NamedElement> ignoreJunction() {

        return junctionLoader.ignoreJunction();
    }

    @Override
    public ElementMatcher.Junction<NamedElement> typeJunction() {
        return junctionLoader.typeJunction();
    }

    @Override
    public ElementMatcher.Junction<NamedElement> methodJunction() {
        return junctionLoader.methodJunction();
    }
}
