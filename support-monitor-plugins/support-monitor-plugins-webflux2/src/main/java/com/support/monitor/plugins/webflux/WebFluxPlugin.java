package com.support.monitor.plugins.webflux;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.webflux.interceptor.WebFluxPluginMethodInterceptor;
import net.bytebuddy.matcher.ElementMatchers;

public class WebFluxPlugin extends AbstractPluginDefine {
    @Override
    public void init(PluginDefineBuilder defineBuilder) {
        defineBuilder.pointName("spring-web-flux")
                .pointClass(ElementMatchers.named("org.springframework.web.reactive.DispatcherHandler"))
                .pointMethod(ElementMatchers.named("handle")
                                .and(ElementMatchers.takesArgument(0, ElementMatchers.named("org.springframework.web.server.ServerWebExchange")))
                        , WebFluxPluginMethodInterceptor.class);
    }
}
