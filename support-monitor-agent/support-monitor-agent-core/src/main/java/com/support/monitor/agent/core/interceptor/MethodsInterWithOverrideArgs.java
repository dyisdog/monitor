package com.support.monitor.agent.core.interceptor;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.apache.commons.lang3.time.StopWatch;

import java.lang.reflect.Method;

/**
 * 方法拦截
 *
 * @author 江浩
 */
public class MethodsInterWithOverrideArgs {

    private AroundInterceptor interceptor;

    public MethodsInterWithOverrideArgs(AroundInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @RuntimeType
    public Object intercept(
            @Origin Class<?> clazz,
            @Origin Method method,
            @AllArguments Object[] allArguments,
            @Morph OverrideCallable callable) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = null;
        try {
            interceptor.beforeMethod(clazz, method, allArguments, method.getParameterTypes());
            result = callable.call(allArguments);
            interceptor.afterMethod(clazz, method, allArguments, method.getParameterTypes(), result);
        } catch (Exception e) {
            interceptor.handleMethodException(clazz, method, allArguments, method.getParameterTypes(), e);
        }
        return result;
    }


}
