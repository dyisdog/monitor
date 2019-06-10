package com.support.monitor.plugins.http;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.http.interceptor.HttpClientMethodInterceptor;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * apache httpClient 4X
 *
 * @author 江浩
 */
public class HttpClient4xPlugin extends AbstractPluginDefine {

    public static final String PLUGIN_NAME = "org.apache.HttpClient4x";


    @Override
    public void init(PluginDefineBuilder defineBuilder) {
        defineBuilder.pointName(PLUGIN_NAME)
                .pointClass(named("org.apache.http.impl.client.MinimalHttpClient")
                        .or(named("org.apache.http.impl.client.InternalHttpClient"))
                        .or(named("org.apache.http.impl.client.AbstractHttpClient")))
                .pointMethod(named("execute"), HttpClientMethodInterceptor.class);
    }
}
