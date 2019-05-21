package com.support.monitor.agent.core.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;

import java.lang.reflect.Method;

public abstract class AbstractAroundInterceptor implements AroundInterceptor {

    public TraceContext traceContext;

    public AbstractAroundInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        final Trace trace = traceContext.newTraceObject();
        if (trace == null) {
            return;
        }

        trace.traceBlockBegin();
        String traceId = trace.getTraceId();
        System.out.println(clazz.getSimpleName() + "  " + method.getName() + "  traceId: " + traceId +
                "\t parentId: " + trace.getParentId() +
                "\t currentId: " + trace.getId() +
                "\t startTime: " + trace.getStartTime()
        );
    }

    @Override
    public void afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        final Trace trace = traceContext.currentTraceObject();
        if (trace == null) {
            return;
        }
        trace.traceBlockEnd();
//        System.out.println(clazz.getSimpleName() + "  " + method.getName() + "  traceId: " + traceId +
//                "\t parentSpanId: " + trace.getCurrentSpan().getParentSpanId() +
//                "\t spanId: " + trace.getCurrentSpan().getSpanId() +
//                "\t startTime: " + trace.getCurrentSpan().getStartTime() +
//                "\t endTime: " + trace.getCurrentSpan().getEndTime() +
//                "\t use: " + (trace.getCurrentSpan().getEndTime() - trace.getCurrentSpan().getStartTime()) + " ms"
//        );

    }


    @Override
    public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {
        t.printStackTrace();
    }


}
