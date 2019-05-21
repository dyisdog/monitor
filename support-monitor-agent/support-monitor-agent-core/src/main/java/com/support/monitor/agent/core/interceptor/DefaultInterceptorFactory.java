package com.support.monitor.agent.core.interceptor;

import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.exception.ConstructorException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * 默认拦截器处理
 *
 * @author 江浩
 */
@Slf4j
public class DefaultInterceptorFactory implements InterceptorFactory {

    private TraceContext traceContext;

    public DefaultInterceptorFactory(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public AroundInterceptor newInterceptor(Class<? extends AroundInterceptor> aroundInterceptorClass) {
        try {
            Constructor<? extends AroundInterceptor> constructor = aroundInterceptorClass.getDeclaredConstructor(TraceContext.class);
            return constructor.newInstance(this.traceContext);
        } catch (Exception e) {
            throw new ConstructorException(String.format("AroundInterceptor Constructor format: %s",
                    aroundInterceptorClass.getSimpleName() + "(com.support.monitor.agent.core.context.trace.TraceContext  args)"));
        }
    }


}
