//package com.support.monitor.plugins.webflux.interceptor;
//
//import com.support.monitor.agent.core.context.TraceContext;
//import com.support.monitor.agent.core.interceptor.InterceptContext;
//import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;
//
///**
// * server operations method interceptor
// *
// * @author 江浩
// */
//public class WebFluxPluginServerOperationsMethodInterceptor extends AbstractMethodAroundInterceptor {
//
//    public WebFluxPluginServerOperationsMethodInterceptor(TraceContext traceContext) {
//        super(traceContext);
//    }
//
//    @Override
//    public void before(InterceptContext interceptContext) {
//
//        try {
//            System.out.println("heeeeeeeeeeel: " + interceptContext.getTarget() + "  " + interceptContext.getMethod());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
