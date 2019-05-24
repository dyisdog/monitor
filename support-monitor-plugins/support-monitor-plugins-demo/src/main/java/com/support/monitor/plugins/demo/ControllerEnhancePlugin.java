package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.demo.interceptor.ControllerInterceptor;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.nameEndsWithIgnoreCase;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author 江浩
 */
public class ControllerEnhancePlugin extends AbstractPluginDefine {
    @Override
    public void init() {
        pointName("Controller拦截插件");
        pointClass(ElementMatchers.nameStartsWithIgnoreCase("com.example.demo")
                .and(nameEndsWithIgnoreCase("Controller"))
        );
        pointMethod(named("test1").or(named("test2")), ControllerInterceptor.class);
    }


}
