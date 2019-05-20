package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.context.PluginSetupContext;
import com.support.monitor.agent.core.plugin.PluginDefine;

public class DemoPlugin implements PluginDefine {
    @Override
    public PluginSetupContext getPluginSetupContext() {
        return new DemoPluginSetupContext();
    }
}
