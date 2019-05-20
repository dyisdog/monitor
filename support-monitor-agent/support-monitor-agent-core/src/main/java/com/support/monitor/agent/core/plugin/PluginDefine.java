package com.support.monitor.agent.core.plugin;

import com.support.monitor.agent.core.context.PluginSetupContext;

/**
 * 插件定义
 *
 * @author 江浩
 */
public interface PluginDefine {

    /**
     * 获取插件设置步骤
     *
     * @return : com.support.monitor.agent.core.context.PluginSetupContext
     * @author 江浩
     */
    PluginSetupContext getPluginSetupContext();

}
