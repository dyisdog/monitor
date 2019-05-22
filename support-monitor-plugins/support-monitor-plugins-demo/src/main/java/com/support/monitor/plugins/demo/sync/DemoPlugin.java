package com.support.monitor.plugins.demo.sync;

import com.support.monitor.agent.core.interceptor.ConstructorInterceptPoint;
import com.support.monitor.agent.core.interceptor.MethodsInterceptPoint;
import com.support.monitor.agent.core.interceptor.enhance.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;
import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

public class DemoPlugin extends AbstractPluginDefine {

    @Override
    public ElementMatcher<? super TypeDescription> classDescription() {
        return ElementMatchers.nameStartsWithIgnoreCase("com.example.demo");
    }

    @Override
    public void init() {

        methodPoint(new MethodsInterceptPoint() {
            @Override
            public ElementMatcher<MethodDescription> getMethodsMatcher() {
                return ElementMatchers.named("test1");
            }

            @Override
            public Class<? extends MethodsAroundInterceptor> getMethodsInterceptor() {
                return DemoPluginMethod1Interceptor.class;
            }

        }).methodPoint(new MethodsInterceptPoint() {
            @Override
            public ElementMatcher<MethodDescription> getMethodsMatcher() {
                return ElementMatchers.named("test2");
            }

            @Override
            public Class<? extends MethodsAroundInterceptor> getMethodsInterceptor() {
                return DemoPluginMethod1Interceptor.class;
            }

        }).constructorPoint(new ConstructorInterceptPoint() {
            @Override
            public ElementMatcher<MethodDescription> getConstructorMatcher() {
                return ElementMatchers.any();
            }

            @Override
            public Class<? extends ConstructorInterceptor> getConstructorInterceptor() {
                return DemoPluginMethod1Interceptor.class;
            }
        });

    }


}
