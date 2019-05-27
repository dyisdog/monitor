package com.support.monitor.plugins.custom;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.custom.interceptor.CustomSdkConstructorInterceptor;
import com.support.monitor.plugins.custom.interceptor.TraceThreadExecuteInterceptor;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * 自定义线程拦截
 *
 * @author 江浩
 */
public class CustomSdkTraceThreadPlugin extends AbstractPluginDefine {

    private static final String SKY_RUNNABLE = "org.apache.skywalking.apm.toolkit.trace.RunnableWrapper";

    @Override
    public void init() {
        pointName("custom-sdk-async");
        pointClass(named(SKY_RUNNABLE));
        pointConstructor(any(), CustomSdkConstructorInterceptor.class);
        pointMethod(named("run"), TraceThreadExecuteInterceptor.class);


    }
}
