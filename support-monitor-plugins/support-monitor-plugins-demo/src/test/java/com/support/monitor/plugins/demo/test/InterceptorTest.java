package com.support.monitor.plugins.demo.test;

import com.support.monitor.agent.core.interceptor.enhance.DefaultInterceptorFactory;
import com.support.monitor.plugins.demo.sync.DemoPluginMethod1Interceptor;

public class InterceptorTest {

    public static void main(String[] args) {
//        Class<DemoPluginMethod1Interceptor> demoPluginMethod1InterceptorClass = DemoPluginMethod1Interceptor.class;
//        Class<DemoPluginMethod2Interceptor> demoPluginMethod2InterceptorClass = DemoPluginMethod2Interceptor.class;
//
//        try {
//            Constructor<DemoPluginMethod1Interceptor> constructor = demoPluginMethod1InterceptorClass.getConstructor(TraceContext.class);
//            System.out.println(constructor);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            Constructor<DemoPluginMethod2Interceptor> constructor = demoPluginMethod2InterceptorClass.getConstructor(TraceContext.class);
//            System.out.println(constructor);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

        DefaultInterceptorFactory defaultInterceptorFactory = new DefaultInterceptorFactory(null);
        defaultInterceptorFactory.newMethodsInterceptor(DemoPluginMethod1Interceptor.class);

    }
}
