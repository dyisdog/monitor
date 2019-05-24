package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.TraceContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author 江浩
 */
@Slf4j
@Getter
public abstract class AbstractMethodsAroundInterceptor implements MethodsAroundInterceptor {

    private final TraceContext traceContext;

    public AbstractMethodsAroundInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
    }

}
