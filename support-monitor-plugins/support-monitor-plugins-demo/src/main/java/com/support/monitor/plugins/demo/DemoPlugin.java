package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.context.AbstractPluginSetupContext;
import com.support.monitor.agent.core.context.PluginSetupContext;
import com.support.monitor.agent.core.plugin.PluginDefine;
import com.support.monitor.plugins.demo.interceptor.DemoPluginMethod1Interceptor;
import net.bytebuddy.matcher.ElementMatchers;

public class DemoPlugin implements PluginDefine {
    @Override
    public PluginSetupContext getPluginSetupContext() {

        return new AbstractPluginSetupContext() {
            @Override
            public String name() {
                return "demo";
            }

            @Override
            public void init() {
                //method 1
                //or .or(ElementMatchers.named("test2"))
                binder(ElementMatchers.nameStartsWithIgnoreCase("com.example.demo"),
                        ElementMatchers.named("test1")
                                .or(ElementMatchers.named("test2")),
                        DemoPluginMethod1Interceptor.class);
            }
        };
    }
}
