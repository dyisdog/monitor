package com.support.monitor.plugins.http.httpclient;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * apache httpClient 4X
 *
 * @author 江浩
 */
public class HttpClient4xPlugin extends AbstractPluginDefine {

    private ElementMatcher<? super TypeDescription> classDescription =
            named(MinimalHttpClient.class.getName());

    @Override
    public void init() {
        pointName("httpClient4x");
        pointClass(classDescription);

    }
}
