package com.support.monitor.plugins.spring;

import com.support.monitor.agent.core.context.AbstractPluginSetupContext;
import com.support.monitor.plugins.spring.plugin.SpringMvcPluginContext;

public class SpringPluginSetupContext extends AbstractPluginSetupContext {

    @Override
    public String getPluginName() {
        return "spring";
    }

    @Override
    public void init() {
        //spring mvc
        super.binder(new SpringMvcPluginContext());
    }


}
