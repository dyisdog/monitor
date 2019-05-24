package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.demo.interceptor.ServiceInterceptor;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author 江浩
 */
public class ServiceEnhancePlugin extends AbstractPluginDefine {
    @Override
    public void init() {
        pointClass(ElementMatchers.nameStartsWithIgnoreCase("com.example.demo.service"));

        pointMethod(ElementMatchers.nameContainsIgnoreCase("test"), ServiceInterceptor.class);
    }

}
