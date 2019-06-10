//package com.support.monitor.plugins.webflux;
//
//import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
//import com.support.monitor.plugins.webflux.interceptor.WebFluxPluginDispatcherHandlerMethodInterceptor;
//
//import static net.bytebuddy.matcher.ElementMatchers.named;
//import static net.bytebuddy.matcher.ElementMatchers.takesArgument;
//
///**
// * webflux
// *
// * @author 江浩
// */
//public class WebFluxPluginHandlerAdaptor extends AbstractPluginDefine {
//    @Override
//    public void init(PluginDefineBuilder defineBuilder) {
//        defineBuilder.pointName("spring-web-flux-handlerAdapter")
//                .pointClass(named("org.springframework.web.reactive.HandlerAdapter"))
//                .pointMethod(named("handle")
//                                .and(
//                                        takesArgument(0,
//                                                named("org.springframework.web.server.ServerWebExchange")
//                                        )
//                                )
//                                .and(
//                                        takesArgument(1,
//                                                named("java.lang.Object")
//                                        )
//                                )
//
//                        , WebFluxPluginDispatcherHandlerMethodInterceptor.class);
//    }
//}
