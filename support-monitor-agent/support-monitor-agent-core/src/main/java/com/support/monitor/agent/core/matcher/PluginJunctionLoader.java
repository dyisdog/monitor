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
    public ElementMatcher.Junction<? super TypeDescription> ignoreJunction() {
        System.out.println("plugin.....ignore");
        super.junctionLoader.ignoreJunction();
        return null;
    }

    @Override
    public ElementMatcher.Junction<? super TypeDescription> typeJunction() {
        System.out.println("plugin.....type");
        return null;
    }

    @Override
    public ElementMatcher.Junction<? super MethodDescription> methodJunction() {
        System.out.println("plugin.....method");
        return null;
    }


}
