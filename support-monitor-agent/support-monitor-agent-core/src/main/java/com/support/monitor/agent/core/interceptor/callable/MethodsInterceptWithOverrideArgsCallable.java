package com.support.monitor.agent.core.interceptor.callable;

import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * 方法拦截
 *
 * @author 江浩
 */
public class MethodsInterceptWithOverrideArgsCallable {

    private MethodsAroundInterceptor interceptor;

    public MethodsInterceptWithOverrideArgsCallable(MethodsAroundInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @RuntimeType
    public Object intercept(
            @This Object object,
            @Origin Method method,
            @AllArguments Object[] allArguments,
            @Morph OverrideCallable callable) throws Exception {

        if (!(object instanceof EnhancedDefine)) {
            return callable.call(allArguments);
        }
        EnhancedDefine enhancedDefine = (EnhancedDefine) object;

        Object result = null;
        try {
            interceptor.beforeMethod(enhancedDefine, method, allArguments, method.getParameterTypes());
            result = callable.call(allArguments);
            interceptor.afterMethod(enhancedDefine, method, allArguments, method.getParameterTypes(), result);
        } catch (Exception e) {
            interceptor.exceptionMethod(enhancedDefine, method, allArguments, method.getParameterTypes(), e);
        }
        return result;
    }


}
