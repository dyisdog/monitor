package com.support.monitor.agent.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.AgentBootStarter;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.config.DefaultAgentConfig;
import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.handler.ApplicationHandler;
import com.support.monitor.agent.core.handler.DefaultApplicationHandler;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;
import com.support.monitor.agent.core.interceptor.delegation.MethodDelegationFactory;
import com.support.monitor.agent.core.module.provider.DelegationFactoryProvider;
import com.support.monitor.agent.core.module.provider.InterceptorProvider;
import com.support.monitor.agent.core.module.provider.TraceContextProvider;
import com.support.monitor.agent.core.plugin.DefaultPluginLoader;
import com.support.monitor.agent.core.plugin.PluginLoader;
import com.support.monitor.agent.core.plugin.adaptor.PluginLoaderAdaptor;
import com.support.monitor.agent.core.plugin.adaptor.SpiPluginLoadAdaptor;
import com.support.monitor.commons.binder.Binder;
import com.support.monitor.commons.binder.ThreadLocalBinder;
import net.bytebuddy.agent.builder.AgentBuilder;

/**
 * 初始模块
 * <p>
 * 使用多个模块不太容易查找
 * </p>
 *
 * @author 江浩
 */
public class InitModule extends AbstractModule {

    public static final String SPI_LOADER_ADAPTOR = "spi";

    @Override
    protected void configure() {

        initBind();

        pluginBind();

        binderBind();

        providerBind();

    }

    private void initBind() {
        bind(AgentBootStarter.class).in(Scopes.SINGLETON);
        bind(AgentConfig.class).to(DefaultAgentConfig.class).in(Scopes.SINGLETON);
        bind(AgentBuilder.class).to(AgentBuilder.Default.class).in(Scopes.SINGLETON);
        bind(ApplicationHandler.class).to(DefaultApplicationHandler.class).in(Scopes.SINGLETON);
    }

    private void binderBind() {
        final TypeLiteral<Binder<Trace>> binder = new TypeLiteral<Binder<Trace>>() {
        };
        final TypeLiteral<ThreadLocalBinder<Trace>> threadLocalBinder = new TypeLiteral<ThreadLocalBinder<Trace>>() {
        };
        bind(binder).to(threadLocalBinder).in(Scopes.SINGLETON);
    }

    private void pluginBind() {
        bind(PluginLoader.class).to(DefaultPluginLoader.class).in(Scopes.SINGLETON);
        bind(PluginLoaderAdaptor.class).annotatedWith(Names.named(SPI_LOADER_ADAPTOR)).to(SpiPluginLoadAdaptor.class).in(Scopes.SINGLETON);
    }

    private void providerBind() {
        bind(TraceContext.class).toProvider(TraceContextProvider.class).in(Scopes.SINGLETON);
        //异步 TODO
//        bind(AsyncTraceContext.class).toProvider(AsyncTraceContextProvider.class).in(Scopes.SINGLETON);
        bind(MethodDelegationFactory.class).toProvider(DelegationFactoryProvider.class).in(Scopes.SINGLETON);
        bind(InterceptorFactory.class).toProvider(InterceptorProvider.class).in(Scopes.SINGLETON);
    }
}
