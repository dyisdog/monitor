package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.interceptor.enhance.DefaultEnhanceFactory;
import com.support.monitor.agent.core.interceptor.enhance.EnhanceFactory;
import com.support.monitor.agent.core.interceptor.enhance.InterceptorFactory;

public class EnhanceFactoryProvider implements Provider<EnhanceFactory> {

    private InterceptorFactory interceptorFactory;

    @Inject
    public EnhanceFactoryProvider(InterceptorFactory interceptorFactory) {
        this.interceptorFactory = interceptorFactory;
    }

    @Override
    public EnhanceFactory get() {
        return new DefaultEnhanceFactory(this.interceptorFactory);
    }
}
