package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 江浩
 */
@Slf4j
@Getter
public abstract class AbstractMethodsAroundInterceptor implements MethodsAroundInterceptor, ConstructorInterceptor {

    private final TraceContext traceContext;

    public AbstractMethodsAroundInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        Trace trace = this.traceContext.currentRawTraceObject();
        System.out.println(enhancedDefine.getClass() + "  " + method.getName() + "  " + trace);
    }

    @Override
    public void onConstruct(EnhancedDefine enhancedDefine, Object[] allArguments) {
        Trace trace = traceContext.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            log.debug("trace: null");
            return;
        }
    }

}
