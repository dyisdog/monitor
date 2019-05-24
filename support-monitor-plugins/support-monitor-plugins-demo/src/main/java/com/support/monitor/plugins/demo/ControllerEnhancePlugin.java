package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.interceptor.MethodsInterceptPoint;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;
import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.demo.interceptor.ControllerInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author 江浩
 */
public class ControllerEnhancePlugin extends AbstractPluginDefine {
    @Override
    public void init() {

        methodPoint(new MethodsInterceptPoint() {
            @Override
            public ElementMatcher<MethodDescription> getMethodsMatcher() {
                return named("test1").or(named("test2"));
            }

            @Override
            public Class<? extends MethodsAroundInterceptor> getMethodsInterceptor() {
                return ControllerInterceptor.class;
            }
        });
    }

    @Override
    public ElementMatcher<? super TypeDescription> classDescription() {
        return ElementMatchers.nameEndsWithIgnoreCase("Controller");
    }
}
