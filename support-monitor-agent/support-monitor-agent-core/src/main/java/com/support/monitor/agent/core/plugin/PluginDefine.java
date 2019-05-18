package com.support.monitor.agent.core.plugin;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

public interface PluginDefine {

    /**
     * 过滤匹配
     *
     * @return : net.bytebuddy.matcher.ElementMatcher<? super net.bytebuddy.description.type.TypeDescription>
     * @author 江浩
     */
    ElementMatcher<? super TypeDescription> ignoreMatcher();

    /**
     * 类型匹配
     *
     * @return
     */
    ElementMatcher<? super TypeDescription> classMatcher();

    /**
     * 方法匹配
     *
     * @return
     */
    ElementMatcher<? super MethodDescription> methodMatcher();

    /**
     * 拦截器
     *
     * @return : com.support.monitor.agent.core.interceptor.AroundInterceptor
     * @author 江浩
     */
    AroundInterceptor interceptor();

    /**
     * 插件名称
     *
     * @return
     */
    String pluginName();

}
