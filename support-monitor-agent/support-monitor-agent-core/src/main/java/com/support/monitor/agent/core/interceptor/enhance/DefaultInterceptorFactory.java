package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.exception.ConstructorException;
import com.support.monitor.agent.core.interceptor.InterceptorAware;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;
import com.support.monitor.agent.core.plugin.PluginDefine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Objects;

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
    public Object newInterceptorObject(String className, PluginDefine pluginDefine) {

        try {
            Class<?> aClass = ClassUtils.getClass(className);
            Object object = null;

            try {
                Constructor<?> constructor = aClass.getDeclaredConstructor(TraceContext.class, PluginDefine.class);
                object = constructor.newInstance(this.traceContext, pluginDefine);
            } catch (NoSuchMethodException e) {
                try {
                    Constructor<?> constructor = aClass.getDeclaredConstructor(TraceContext.class);
                    object = constructor.newInstance(this.traceContext);

                    invokeMethod(pluginDefine, aClass, object);

                } catch (NoSuchMethodException ignored) {
                }
            }

            if (Objects.isNull(object)) {
                throw builder(className);
            }

            return object;
        } catch (Exception e) {
            throw builder(className);
        }
    }

    private void invokeMethod(PluginDefine pluginDefine, Class<?> aClass, Object object) {

        if (InterceptorAware.class.isAssignableFrom(aClass)) {
            try {
                Method method = aClass.getMethod("interceptorWithPlugin", PluginDefine.class);
                method.invoke(object, pluginDefine);
            } catch (Exception ignored) {
            }
        }
    }


    private ConstructorException builder(String className) {
        return new ConstructorException(String.format("AroundInterceptor Constructor format: %s",
                className + "(com.support.monitor.agent.core.context.trace.TraceContext  arg0)") + "\n or \n" +
                String.format("AroundInterceptor Constructor format: %s",
                        className + "(com.support.monitor.agent.core.context.trace.TraceContext  arg0,com.support.monitor.agent.core.plugin.PluginDefine arg1)"));
    }


}
