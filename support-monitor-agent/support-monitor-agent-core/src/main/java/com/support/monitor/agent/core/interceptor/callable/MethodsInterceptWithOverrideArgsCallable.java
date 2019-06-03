package com.support.monitor.agent.core.interceptor.callable;

import com.support.monitor.agent.core.interceptor.MethodAroundInterceptor;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * 方法拦截
 *
 * @author 江浩
 */
public class MethodsInterceptWithOverrideArgsCallable {

    private MethodAroundInterceptor methodAroundInterceptor;

    public MethodsInterceptWithOverrideArgsCallable(MethodAroundInterceptor methodAroundInterceptor) {
        this.methodAroundInterceptor = methodAroundInterceptor;
    }

    @RuntimeType
    public Object intercept(
            @This Object object,
            @Origin Method method,
            @AllArguments Object[] allArguments,
            @Morph OverrideCallable callable) throws Exception {

        Object result = null;
        try {
            methodAroundInterceptor.before(object, method, allArguments, method.getParameterTypes());
            result = callable.invoker(allArguments);
            methodAroundInterceptor.after(object, method, allArguments, method.getParameterTypes(), result);
        } catch (Exception e) {
            methodAroundInterceptor.exception(object, method, allArguments, method.getParameterTypes(), e);
        }
        return result;
    }


}
