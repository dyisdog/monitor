//package com.support.monitor.plugins.webflux.interceptor;
//
//import com.alipay.common.tracer.core.context.span.SofaTracerSpanContext;
//import com.support.monitor.agent.core.context.RemoteTransmission;
//import com.support.monitor.agent.core.context.TraceContext;
//import com.support.monitor.agent.core.interceptor.InterceptContext;
//import com.support.monitor.agent.core.interceptor.supper.AbstractTransmissionMethodAroundInterceptor;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.web.server.ServerWebExchange;
//
//import java.util.Objects;
//
///**
// * @author 江浩
// */
//public class WebFluxPluginDispatcherHandlerMethodInterceptor extends AbstractTransmissionMethodAroundInterceptor<ServerHttpRequest> {
//
//    static final String IGNORED_URL = ".*favicon.ico.*";
//
//    public WebFluxPluginDispatcherHandlerMethodInterceptor(TraceContext traceContext) {
//        super(traceContext);
//        setRemoteTransmission(new RemoteTransmission<ServerHttpRequest>() {
//            @Override
//            public SofaTracerSpanContext receive(ServerHttpRequest handler, String key) {
//                String spanContext = handler.getHeaders().getFirst(key);
//                return convert(spanContext);
//            }
//        });
//    }
//
//    @Override
//    public void before(InterceptContext interceptContext) {
//        Object[] args = interceptContext.getArgs();
//
//        if (Objects.isNull(args) || args.length <= 0) {
//            return;
//        }
//        Object arg0 = args[0];
//        if (!(arg0 instanceof ServerWebExchange)) {
//            return;
//        }
//        ServerWebExchange serverWebExchange = (ServerWebExchange) arg0;
//
//        interceptContext.setIgnored(serverWebExchange.getRequest().getURI().toString().matches(IGNORED_URL));
//        if (interceptContext.isIgnored()) {
//            return;
//        }
//
//        super.currentTracerSpan(serverWebExchange.getRequest(), interceptContext);
//    }
//
//    @Override
//    public void after(InterceptContext interceptContext) {
//
//        if (interceptContext.isIgnored()) {
//            return;
//        }
//        //TODO reactor 方式的处理逻辑？
//        super.after(interceptContext);
//    }
//}
