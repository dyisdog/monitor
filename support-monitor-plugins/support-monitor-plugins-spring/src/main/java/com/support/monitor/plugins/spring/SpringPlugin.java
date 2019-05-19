package com.support.monitor.plugins.spring;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import com.support.monitor.agent.core.interceptor.TargetInterceptPoint;
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

    @Override
    public TargetInterceptPoint targetInterceptPoint() {
        return new TargetInterceptPoint() {
            @Override
            public ElementMatcher<? super TypeDescription> classMatcher() {
                return ElementMatchers.any();
            }

            @Override
            public ElementMatcher<? super MethodDescription> methodMatcher() {
                return ElementMatchers.any();
            }
        };
    }

    @Override
    public AroundInterceptor aroundInterceptor() {
        return new SpringAroundInterceptor();
    }
}
