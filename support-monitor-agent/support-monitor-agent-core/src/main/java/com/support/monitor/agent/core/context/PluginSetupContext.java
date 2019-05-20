package com.support.monitor.agent.core.context;

import com.support.monitor.agent.core.interceptor.PluginInterceptor;

import java.util.List;

/**
 * 插件设置
 * <p>
 * 同一个插件可能存在多个不同的处理方式
 * </p>
 *
 * @author 江浩
 */
public interface PluginSetupContext {


    /**
     * 获取插件名称
     *
     * @return : java.lang.String
     * @author 江浩
     */
    String getPluginName();

    /**
     * 插件拦截器
     *
     * @return : java.util.List<com.support.monitor.agent.core.interceptor.PluginInterceptor>
     * @author 江浩
     */
    List<PluginInterceptor> interceptors();

}
