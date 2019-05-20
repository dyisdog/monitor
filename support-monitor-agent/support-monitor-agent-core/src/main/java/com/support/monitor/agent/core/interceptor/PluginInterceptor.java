package com.support.monitor.agent.core.interceptor;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.UUID;

/**
 * 插件拦截器
 *
 * @author 江浩
 */
public interface PluginInterceptor extends AroundInterceptor {

    /**
     * 插件拦截器名称
     *
     * @return : java.lang.String
     * @author 江浩
     */
    default String name() {
        return UUID.randomUUID().toString();
    }

    /**
     * 拦截类型
     *
     * @return : net.bytebuddy.matcher.ElementMatcher<? super net.bytebuddy.description.type.TypeDescription>
     * @author 江浩
     */
    default ElementMatcher<? super TypeDescription> classInterceptor() {
        return ElementMatchers.any();
    }


    /**
     * 拦截方法
     *
     * @return : net.bytebuddy.matcher.ElementMatcher<? super net.bytebuddy.description.type.MethodDescription>
     * @author 江浩
     */
    default ElementMatcher<? super MethodDescription> methodInterceptor() {
        return ElementMatchers.any();
    }
}
