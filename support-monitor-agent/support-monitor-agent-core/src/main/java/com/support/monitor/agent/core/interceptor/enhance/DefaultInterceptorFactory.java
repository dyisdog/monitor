package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.exception.ConstructorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.InvocationTargetException;

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
    public Object newInterceptorObject(String className) {
        try {
            return ClassUtils.getClass(className).getDeclaredConstructor(TraceContext.class).newInstance(this.traceContext);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ConstructorException(String.format("AroundInterceptor Constructor format: %s",
                    className + "(com.support.monitor.agent.core.context.trace.TraceContext  args)"));
        }
    }
}
