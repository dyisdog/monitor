package com.support.monitor.agent.core.interceptor.delegation;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedInstance;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * 方法拦截
 *
 * @author 江浩
 */
public class MethodsInterceptWithOverrideArgs {

    private AroundInterceptor interceptor;

    public MethodsInterceptWithOverrideArgs(AroundInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @RuntimeType
    public Object intercept(
            @This Object object,
            @Origin Method method,
            @AllArguments Object[] allArguments,
            @Morph OverrideCallable callable) throws Exception {

        System.out.println(object + "=========>>" + (object instanceof EnhancedInstance));
        Object result = null;
        try {
            //interceptor.beforeMethod(clazz, method, allArguments, method.getParameterTypes());
            result = callable.call(allArguments);
            // interceptor.afterMethod(clazz, method, allArguments, method.getParameterTypes(), result);
        } catch (Exception e) {
            // interceptor.handleMethodException(clazz, method, allArguments, method.getParameterTypes(), e);
        }
        return result;
    }


}
