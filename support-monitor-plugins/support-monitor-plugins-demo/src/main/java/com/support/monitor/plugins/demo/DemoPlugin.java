package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.interceptor.MethodsInterceptPoint;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;
import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 测试插件
 *
 * @author 江浩
 */
public class DemoPlugin extends AbstractPluginDefine {
    @Override
    public void init() {

        methodPoint(new MethodsInterceptPoint() {
            @Override
            public ElementMatcher<MethodDescription> getMethodsMatcher() {
                return ElementMatchers.nameContainsIgnoreCase("test");
            }

            @Override
            public Class<? extends MethodsAroundInterceptor> getMethodsInterceptor() {
                return DemoPluginInterceptor.class;
            }
        });
    }

    @Override
    public ElementMatcher<? super TypeDescription> classDescription() {
        return ElementMatchers.nameStartsWithIgnoreCase("com.example.demo");
    }
}
