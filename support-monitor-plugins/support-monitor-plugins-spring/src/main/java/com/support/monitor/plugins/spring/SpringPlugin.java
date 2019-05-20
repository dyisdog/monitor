package com.support.monitor.plugins.spring;

import com.support.monitor.agent.core.context.PluginSetupContext;
import com.support.monitor.agent.core.plugin.PluginDefine;

/**
 * spring 插件
 *
 * @author 江浩
 */
public class SpringPlugin implements PluginDefine {

    private static final String PLUGIN_NAME = "Spring";

    @Override
    public PluginSetupContext getPluginSetupContext() {
        return new SpringPluginSetupContext();
    }
}
