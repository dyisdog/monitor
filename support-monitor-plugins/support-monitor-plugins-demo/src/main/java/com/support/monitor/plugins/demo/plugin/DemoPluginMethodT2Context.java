package com.support.monitor.plugins.demo.plugin;

import com.support.monitor.agent.core.context.PluginContext;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * demo 第一个测试插件拦截类容
 */
public class DemoPluginMethodT2Context implements PluginContext {

    @Override
    public ElementMatcher<? super TypeDescription> classMatcher() {
        return ElementMatchers.nameStartsWithIgnoreCase("com.example.demo");
    }

    @Override
    public ElementMatcher<? super MethodDescription> methodMatcher() {
        return ElementMatchers.named("test2");
    }

    @Override
    public AroundInterceptor aroundInterceptor() {
        return new DemoPluginMethod1AroundInterceptor();
    }

    @Override
    public TraceContext traceContext() {
        return null;
    }
}