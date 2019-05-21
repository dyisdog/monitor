package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;
import com.support.monitor.agent.core.interceptor.delegation.DefaultMethodDelegationFactory;
import com.support.monitor.agent.core.interceptor.delegation.MethodDelegationFactory;

public class DelegationFactoryProvider implements Provider<MethodDelegationFactory> {

    private InterceptorFactory interceptorFactory;

    @Inject
    public DelegationFactoryProvider(InterceptorFactory interceptorFactory) {
        this.interceptorFactory = interceptorFactory;
    }

    @Override
    public MethodDelegationFactory get() {
        return new DefaultMethodDelegationFactory(this.interceptorFactory);
    }
}
