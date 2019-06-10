package com.support.monitor.plugins.webflux.interceptor;

import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;

public class OnOutboundCompleteMethodInterceptor extends AbstractMethodAroundInterceptor {
    public OnOutboundCompleteMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void before(InterceptContext interceptContext) {
        System.out.println("onOutboundComplete..........");
    }

    @Override
    public void after(InterceptContext interceptContext) {
        System.out.println("onOutboundComplete after....");
    }
}
