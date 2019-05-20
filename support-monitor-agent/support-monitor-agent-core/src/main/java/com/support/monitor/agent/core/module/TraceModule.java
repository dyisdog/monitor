package com.support.monitor.agent.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.module.provider.TraceContextProvider;
import com.support.monitor.commons.binder.Binder;
import com.support.monitor.commons.binder.ThreadLocalBinder;

/**
 * trace Module
 *
 * @author 江浩
 */
public class TraceModule extends AbstractModule {
    @Override
    protected void configure() {
        final TypeLiteral<Binder<Trace>> binder = new TypeLiteral<Binder<Trace>>() {
        };
        final TypeLiteral<ThreadLocalBinder<Trace>> threadLocalBinder = new TypeLiteral<ThreadLocalBinder<Trace>>() {
        };
        bind(binder).to(threadLocalBinder).in(Scopes.SINGLETON);
        bind(TraceContext.class).toProvider(TraceContextProvider.class).in(Scopes.SINGLETON);
//        bind(AsyncTraceContext.class).toProvider(AsyncTraceContextProvider.class).in(Scopes.SINGLETON);
    }
}
