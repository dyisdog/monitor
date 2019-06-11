package com.support.monitor.plugins.okhttp;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.okhttp.interceptor.OkHttpRealCallMethodInterceptor;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class OkHttpRealCallPlugin extends AbstractPluginDefine {
    @Override
    public void init(PluginDefineBuilder defineBuilder) {
        defineBuilder.pointName("plugin-okHttp-realCall")
                .pointClass(named("okhttp3.RealCall"))
                .pointMethod(named("execute")
                                .or(named("enqueue"))
                                .or(named("cancel")),
                        OkHttpRealCallMethodInterceptor.class
                );
    }
}
