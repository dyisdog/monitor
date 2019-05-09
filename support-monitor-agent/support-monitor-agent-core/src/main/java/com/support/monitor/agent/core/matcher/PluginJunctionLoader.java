package com.support.monitor.agent.core.matcher;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
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
    public ElementMatcher.Junction<TypeDescription> ignoreJunction() {
        return junctionLoader.ignoreJunction();
    }

    @Override
    public ElementMatcher.Junction<TypeDescription> typeJunction() {
        return junctionLoader.typeJunction();
    }

    @Override
    public ElementMatcher.Junction<MethodDescription> methodJunction() {
        return junctionLoader.methodJunction();
    }
}
