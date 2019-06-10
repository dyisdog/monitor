//package com.support.monitor.plugins.webflux;
//
//import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
//import com.support.monitor.plugins.webflux.interceptor.WebFluxPluginServerOperationsMethodInterceptor;
//
//import static net.bytebuddy.matcher.ElementMatchers.named;
//
///**
// * @author 江浩
// */
//public class WebFluxPluginServerOperations extends AbstractPluginDefine {
//    @Override
//    public void init(PluginDefineBuilder defineBuilder) {
//
//        //protected void onInboundNext(ChannelHandlerContext ctx, Object msg)
//        defineBuilder.pointName("spring-web-flux-serverOperations")
//                .pointClass(named("reactor.netty.http.server.HttpServerOperations"))
//                .pointMethod(named("onInboundNext"), WebFluxPluginServerOperationsMethodInterceptor.class);
//    }
//}
