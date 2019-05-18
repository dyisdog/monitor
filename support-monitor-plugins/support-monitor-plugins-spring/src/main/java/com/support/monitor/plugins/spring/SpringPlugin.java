package com.support.monitor.plugins.spring;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import com.support.monitor.agent.core.plugin.PluginDefine;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * spring 插件
 *
 * @author 江浩
 */
public class SpringPlugin implements PluginDefine {

    private static final String DEFAULT_PLUGIN_NAME = "Spring";


    @Override
    public ElementMatcher<? super TypeDescription> ignoreMatcher() {
        return ElementMatchers.none();
    }

    @Override
    public ElementMatcher<? super TypeDescription> classMatcher() {
        return ElementMatchers.nameMatches("org.springframework.context.support.*")
                .or(ElementMatchers.nameMatches("org.springframework.web.*"));
    }

    @Override
    public ElementMatcher<? super MethodDescription> methodMatcher() {
        return ElementMatchers.any();
    }

    @Override
    public AroundInterceptor interceptor() {
        return new SpringAroundInterceptor();
    }

    @Override
    public String pluginName() {
        return DEFAULT_PLUGIN_NAME;
    }
}
