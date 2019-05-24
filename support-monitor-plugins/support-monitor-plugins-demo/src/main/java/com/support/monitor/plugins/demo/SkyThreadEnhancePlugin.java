package com.support.monitor.plugins.demo;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.demo.interceptor.SkyAfterThreadInterceptor;
import com.support.monitor.plugins.demo.interceptor.SkyBeforeThreadInterceptor;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.any;

/**
 * 异步方法增强
 *
 * @author 江浩
 */
public class SkyThreadEnhancePlugin extends AbstractPluginDefine {

    @Override
    public void init() {
        pointClass(ElementMatchers.nameStartsWithIgnoreCase("org.apache.skywalking.apm.toolkit.trace.RunnableWrapper"));
        pointConstructor(any(), SkyBeforeThreadInterceptor.class);
        pointMethod(ElementMatchers.named("run"), SkyAfterThreadInterceptor.class);
    }

}
