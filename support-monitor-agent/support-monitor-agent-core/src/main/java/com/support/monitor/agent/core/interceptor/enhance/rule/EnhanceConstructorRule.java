package com.support.monitor.agent.core.interceptor.enhance.rule;

import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.interceptor.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.callable.ConstructorInterceptCallable;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;

/**
 * 构造器增强
 *
 * @author 江浩
 */
public class EnhanceConstructorRule extends AbstractEnhanceRule<ConstructorInterceptor> {


    @Override
    protected DynamicType.Builder<?> enhanceDefine(DynamicType.Builder<?> builder, ConstructorInterceptor interceptPoint, EnhanceContext enhanceContext) {
        MethodDelegation methodDelegation = constructorDelegation(interceptPoint);
        return builder.constructor(enhanceContext.getMethodDescription()).intercept(SuperMethodCall.INSTANCE
                .andThen(methodDelegation)
        );
    }

    private MethodDelegation constructorDelegation(ConstructorInterceptor constructorInterceptor) {
        return
                MethodDelegation.withDefaultConfiguration()
                        .to(new ConstructorInterceptCallable(constructorInterceptor));
    }

}
