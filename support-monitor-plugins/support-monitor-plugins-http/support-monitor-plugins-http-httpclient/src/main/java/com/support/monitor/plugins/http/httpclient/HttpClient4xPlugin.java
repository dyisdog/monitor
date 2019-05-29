package com.support.monitor.plugins.http.httpclient;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.http.httpclient.interceptor.HttpClientMethodInterceptor;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * apache httpClient 4X
 *
 * @author 江浩
 */
public class HttpClient4xPlugin extends AbstractPluginDefine {

    @Override
    public void init() {
        pointName("org.apache.HttpClient4x");
        pointClass(named("org.apache.http.impl.client.MinimalHttpClient")
                .or(named("org.apache.http.impl.client.InternalHttpClient"))
                .or(named("org.apache.http.impl.client.AbstractHttpClient")));
        pointMethod(named("execute"), HttpClientMethodInterceptor.class);
    }
}
