package com.support.monitor.agent.core.interceptor;

import com.support.monitor.agent.core.plugin.PluginDefine;

/**
 * @author 江浩
 */
public interface InterceptorAware {

    /**
     * 拦截器关联的插件信息
     *
     * @param pluginDefine :
     * @return : void
     * @author 江浩
     */
    default void interceptorWithPlugin(PluginDefine pluginDefine) {
    }

}
