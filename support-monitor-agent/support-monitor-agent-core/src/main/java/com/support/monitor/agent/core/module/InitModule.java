package com.support.monitor.agent.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.support.monitor.agent.core.module.provider.ObjectBinderFactory;
import com.support.monitor.agent.core.module.provider.ObjectBinderFactoryProvider;

public class InitModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ObjectBinderFactory.class).toProvider(ObjectBinderFactoryProvider.class).in(Scopes.SINGLETON);
    }
}
