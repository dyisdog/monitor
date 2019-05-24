package com.support.monitor.agent.core.interceptor.enhance.rule;

import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.interceptor.callable.MethodsInterceptWithOverrideArgsCallable;
import com.support.monitor.agent.core.interceptor.callable.OverrideCallable;
import com.support.monitor.agent.core.interceptor.enhance.MethodsAroundInterceptor;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.isStatic;

/**
 * 方法增强
 *
 * @author 江浩
 */
public class EnhanceMethodRule extends AbstractEnhanceRule<MethodsAroundInterceptor> {


    @Override
    protected DynamicType.Builder<?> enhanceDefine(DynamicType.Builder<?> builder, MethodsAroundInterceptor interceptPoint, EnhanceContext enhanceContext) {
        MethodDelegation methodDelegation = methodsWithOverrideArgsDelegation(interceptPoint);
        return builder.method(ElementMatchers.not(isStatic()).and(enhanceContext.getMethodDescription())).intercept(methodDelegation);

    }

    private MethodDelegation methodsWithOverrideArgsDelegation(MethodsAroundInterceptor methodsAroundInterceptor) {
        return MethodDelegation.withDefaultConfiguration()
                .withBinders(
                        Morph.Binder.install(OverrideCallable.class)
                )
                .to(new MethodsInterceptWithOverrideArgsCallable(methodsAroundInterceptor));
    }

}
