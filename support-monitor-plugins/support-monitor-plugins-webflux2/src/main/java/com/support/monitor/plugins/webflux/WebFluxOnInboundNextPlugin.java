package com.support.monitor.plugins.webflux;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.webflux.interceptor.OnInboundNextMethodInterceptor;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * reactor
 *
 * @author 江浩
 */
public class WebFluxOnInboundNextPlugin extends AbstractPluginDefine {
    @Override
    public void init(PluginDefineBuilder defineBuilder) {

        defineBuilder
                .pointName("spring-web-flux-onInboundNext")
                .pointClass(named("reactor.netty.http.server.HttpServerOperations"))
                .pointMethod(named("onInboundNext"), OnInboundNextMethodInterceptor.class);
    }
}
