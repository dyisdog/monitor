package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.context.AbstractPluginSetupContext;
import com.support.monitor.plugins.demo.plugin.DemoPluginMethodT1Context;

public class DemoPluginSetupContext extends AbstractPluginSetupContext {
    @Override
    public String getPluginName() {
        return "Demo";
    }

    @Override
    public void init() {
        //method test1
        binder(new DemoPluginMethodT1Context());
        //method test2
        //static method ?
    }
}
