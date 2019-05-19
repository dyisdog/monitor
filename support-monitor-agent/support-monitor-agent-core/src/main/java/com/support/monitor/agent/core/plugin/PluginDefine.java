package com.support.monitor.agent.core.plugin;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import com.support.monitor.agent.core.interceptor.TargetInterceptPoint;

/**
 * 插件定义
 *
 * @author 江浩
 */
public interface PluginDefine {

    /**
     * 目标拦截
     *
     * @return : com.support.monitor.agent.core.interceptor.TargetInterceptPoint
     * @author 江浩
     */
    TargetInterceptPoint targetInterceptPoint();

    /**
     * 环绕拦截 {@link #targetInterceptPoint()}
     *
     * @return : com.support.monitor.agent.core.interceptor.AroundInterceptor
     * @author 江浩
     */
    AroundInterceptor aroundInterceptor();

}
