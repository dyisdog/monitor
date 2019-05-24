package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.interceptor.ConstructorInterceptPoint;
import com.support.monitor.agent.core.interceptor.MethodsInterceptPoint;
import com.support.monitor.agent.core.interceptor.enhance.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;
import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.demo.interceptor.SkyAfterThreadInterceptor;
import com.support.monitor.plugins.demo.interceptor.SkyBeforeThreadInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.any;

/**
 * 异步方法增强
 *
 * @author 江浩
 */
public class SkyThreadEnhancePlugin extends AbstractPluginDefine {

    @Override
    public void init() {
        methodPoint(new MethodsInterceptPoint() {
            @Override
            public ElementMatcher<MethodDescription> getMethodsMatcher() {
                return ElementMatchers.named("run");
            }

            @Override
            public Class<? extends MethodsAroundInterceptor> getMethodsInterceptor() {
                return SkyAfterThreadInterceptor.class;
            }
        }).constructorPoint(new ConstructorInterceptPoint() {
            @Override
            public ElementMatcher<MethodDescription> getConstructorMatcher() {
                return any();
            }

            @Override
            public Class<? extends ConstructorInterceptor> getConstructorInterceptor() {
                return SkyBeforeThreadInterceptor.class;
            }
        });
    }

    @Override
    public ElementMatcher<? super TypeDescription> classDescription() {
        return ElementMatchers.nameStartsWithIgnoreCase("org.apache.skywalking.apm.toolkit.trace.RunnableWrapper");
    }
}
