package com.support.monitor.agent.core.interceptor.callable;

import com.support.monitor.agent.core.interceptor.InterceptContext;
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
        Throwable throwable = null;

        InterceptContext interceptContext = InterceptContext.builder()
                .target(object)
                .method(method)
                .args(allArguments)
                .build();

        try {
            methodAroundInterceptor.before(interceptContext);
            result = callable.invoker(allArguments);
        } catch (Exception e) {
            throwable = e;
        } finally {
            interceptContext.setResult(result);
            interceptContext.setThrowable(throwable);
            methodAroundInterceptor.after(interceptContext);
        }

        return result;
    }


}
