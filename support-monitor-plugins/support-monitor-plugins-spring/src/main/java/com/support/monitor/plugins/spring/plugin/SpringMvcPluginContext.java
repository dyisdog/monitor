package com.support.monitor.plugins.spring.plugin;

import com.support.monitor.agent.core.context.PluginContext;
import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

public class SpringMvcPluginContext implements PluginContext {

    @Override
    public ElementMatcher<? super TypeDescription> classMatcher() {
        return ElementMatchers.nameMatches("org.springframework.mvc.*");
    }

    @Override
    public ElementMatcher<? super MethodDescription> methodMatcher() {
        return ElementMatchers.any();
    }

    @Override
    public AroundInterceptor aroundInterceptor() {
        return new SpringMvcAroundInterceptor();
    }
}
