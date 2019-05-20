package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.context.AbstractPluginSetupContext;
import com.support.monitor.agent.core.context.PluginSetupContext;
import com.support.monitor.agent.core.plugin.PluginDefine;
import com.support.monitor.plugins.demo.interceptor.DemoPluginMethod1Interceptor;
import com.support.monitor.plugins.demo.interceptor.DemoPluginMethod2Interceptor;

public class DemoPlugin implements PluginDefine {
    @Override
    public PluginSetupContext getPluginSetupContext() {

        return new AbstractPluginSetupContext() {
            @Override
            public void init() {
                //method 1
                binder(new DemoPluginMethod1Interceptor());
                //method 2
                binder(new DemoPluginMethod2Interceptor());
            }

            @Override
            public String getPluginName() {
                return "Demo";
            }
        };
    }
}
