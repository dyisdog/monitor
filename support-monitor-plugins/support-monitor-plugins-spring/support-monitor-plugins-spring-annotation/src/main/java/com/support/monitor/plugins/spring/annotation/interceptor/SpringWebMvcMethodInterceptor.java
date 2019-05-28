package com.support.monitor.plugins.spring.annotation.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.span.SpanEvent;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;

import java.lang.reflect.Method;
import java.util.Objects;

public class SpringWebMvcMethodInterceptor extends AbstractMethodAroundInterceptor {


    public SpringWebMvcMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }


    @Override
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        /**
         * 如果其他远程调用并没有传递trace信息过来，mvc作为入口？
         */
        Trace trace = getTraceContext().getOrNewTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        trace.traceBegin(SpanEvent.builder()
                .eventTarget(enhancedDefine.getClass().getName())
                .eventMethod(method.getName())
                .build());
        this.doBefore(trace, enhancedDefine, method, allArguments, parameterTypes);
    }

    @Override
    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        print(trace);
    }
}
