package com.support.monitor.agent.core.context;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 插件环境
 *
 * @author 江浩
 */
public interface PluginContext {


    /**
     * 插件下面多个配置标识
     *
     * @return
     */
    String tag();


    /**
     * 拦截类
     *
     * @return : net.bytebuddy.matcher.ElementMatcher<? super net.bytebuddy.description.type.TypeDescription>
     * @author 江浩
     */
    ElementMatcher<? super TypeDescription> classMatcher();

    /**
     * 拦截方法
     *
     * @return
     */
    ElementMatcher<? super MethodDescription> methodMatcher();

    /**
     * 拦截执行
     *
     * @return : com.support.monitor.agent.core.interceptor.AroundInterceptor
     * @author 江浩
     */
    AroundInterceptor aroundInterceptor();
}
