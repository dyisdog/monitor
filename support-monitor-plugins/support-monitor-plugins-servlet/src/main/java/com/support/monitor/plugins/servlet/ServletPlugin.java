package com.support.monitor.plugins.servlet;

import com.support.monitor.agent.core.context.AbstractPluginSetupContext;
import com.support.monitor.agent.core.context.PluginSetupContext;
import com.support.monitor.agent.core.plugin.PluginDefine;
import com.support.monitor.plugins.servlet.interceptor.JavaxServletPluginInterceptor;

public class ServletPlugin implements PluginDefine {

    private static final String PLUGIN_NAME = "javax.servlet";

    @Override
    public PluginSetupContext getPluginSetupContext() {
        return new AbstractPluginSetupContext() {
            @Override
            public void init() {

                binder(new JavaxServletPluginInterceptor());

            }

            @Override
            public String getPluginName() {
                return PLUGIN_NAME;
            }
        };
    }
}
