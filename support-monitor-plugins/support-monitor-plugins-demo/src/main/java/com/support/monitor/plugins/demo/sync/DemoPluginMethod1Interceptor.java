package com.support.monitor.plugins.demo.sync;

import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.AbstractMethodsAroundInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

public class DemoPluginMethod1Interceptor extends AbstractMethodsAroundInterceptor {

    public DemoPluginMethod1Interceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void onConstruct(EnhancedDefine enhancedDefine, Object[] allArguments) {
        System.out.println("construct:  " + enhancedDefine.getClass());
    }
}
