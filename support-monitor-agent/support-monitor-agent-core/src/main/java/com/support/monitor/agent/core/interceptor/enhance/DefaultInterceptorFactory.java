package com.support.monitor.agent.core.interceptor.enhance;

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
    public MethodsAroundInterceptor newMethodsInterceptor(Class<? extends MethodsAroundInterceptor> aroundInterceptorClass) {
        try {
            Constructor<? extends MethodsAroundInterceptor> constructor = aroundInterceptorClass.getDeclaredConstructor(TraceContext.class);
            return constructor.newInstance(this.traceContext);
        } catch (Exception e) {
            throw new ConstructorException(String.format("AroundInterceptor Constructor format: %s",
                    aroundInterceptorClass.getSimpleName() + "(com.support.monitor.agent.core.context.trace.TraceContext  args)"));
        }
    }

    @Override
    public ConstructorInterceptor newConstructorInterceptor(Class<? extends ConstructorInterceptor> constructorInterceptorClass) {
        try {
            Constructor<? extends ConstructorInterceptor> constructor = constructorInterceptorClass.getDeclaredConstructor(TraceContext.class);
            return constructor.newInstance(this.traceContext);
        } catch (Exception e) {
            throw new ConstructorException(String.format("AroundInterceptor Constructor format: %s",
                    constructorInterceptorClass.getSimpleName() + "(com.support.monitor.agent.core.context.trace.TraceContext  args)"));
        }
    }


}
