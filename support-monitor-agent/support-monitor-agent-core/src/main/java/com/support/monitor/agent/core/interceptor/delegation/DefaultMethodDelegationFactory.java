package com.support.monitor.agent.core.interceptor.delegation;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Morph;

/**
 * 默认method delegation构建工厂
 *
 * @author
 */
public class DefaultMethodDelegationFactory implements MethodDelegationFactory {

    private InterceptorFactory interceptorFactory;

    public DefaultMethodDelegationFactory(InterceptorFactory interceptorFactory) {
        this.interceptorFactory = interceptorFactory;
    }

    @Override
    public MethodDelegation defaultMethodDelegation(Class<? extends AroundInterceptor> aroundInterceptorClass) {

        AroundInterceptor aroundInterceptor = interceptorFactory.newInterceptor(aroundInterceptorClass);

        return MethodDelegation.withDefaultConfiguration()
                // 覆写参数
                .withBinders(
                        Morph.Binder.install(OverrideCallable.class)
                )
                .to(new MethodsInterceptWithOverrideArgs(aroundInterceptor));
    }
}
