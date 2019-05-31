package com.support.monitor.agent.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.AgentBootStarter;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.trace.*;
import com.support.monitor.agent.core.context.trace.id.DefaultIdGenerator;
import com.support.monitor.agent.core.context.trace.id.DefaultTraceIdFactory;
import com.support.monitor.agent.core.context.trace.id.IdGenerator;
import com.support.monitor.agent.core.context.trace.id.TraceIdFactory;
import com.support.monitor.agent.core.context.trace.recorder.DefaultRecorderFactory;
import com.support.monitor.agent.core.context.trace.recorder.RecorderFactory;
import com.support.monitor.agent.core.context.trace.span.SpanFactory;
import com.support.monitor.agent.core.debug.EnhanceDebugFactory;
import com.support.monitor.agent.core.handler.ApplicationHandler;
import com.support.monitor.agent.core.handler.DefaultApplicationHandler;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;
import com.support.monitor.agent.core.interceptor.enhance.EnhanceFactory;
import com.support.monitor.agent.core.interceptor.enhance.rule.*;
import com.support.monitor.agent.core.module.provider.*;
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

        enhanceRule();

    }

    private void initBind() {
        bind(AgentBootStarter.class).in(Scopes.SINGLETON);

        bind(AgentConfig.class).toProvider(AgentConfigProvider.class).in(Scopes.SINGLETON);
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
        //bind(PluginLoader.class).toProvider(PluginLoaderProvider.class).in(Scopes.SINGLETON);
        //bind(PluginLoaderAdaptor.class).annotatedWith(Names.named(SPI_LOADER_ADAPTOR)).to(SpiPluginLoadAdaptor.class).in(Scopes.SINGLETON);
    }

    private void providerBind() {
        bind(TraceContext.class).toProvider(TraceContextProvider.class).in(Scopes.SINGLETON);

        bind(IdGenerator.class).to(DefaultIdGenerator.class).in(Scopes.SINGLETON);
        bind(SpanFactory.class).toProvider(SpanFactoryProvider.class).in(Scopes.SINGLETON);

        bind(Depth.class).to(DefaultDepth.class).in(Scopes.SINGLETON);
        bind(TraceIdFactory.class).to(DefaultTraceIdFactory.class).in(Scopes.SINGLETON);
        bind(TraceFactory.class).toProvider(TraceFactoryProvider.class).in(Scopes.SINGLETON);

        bind(EnhanceFactory.class).toProvider(EnhanceFactoryProvider.class).in(Scopes.SINGLETON);
        bind(EnhanceDebugFactory.class).toProvider(EnhanceDebugFactoryProvider.class).in(Scopes.SINGLETON);

        bind(InterceptorFactory.class).toProvider(InterceptorProvider.class).in(Scopes.SINGLETON);

        bind(RecorderFactory.class).to(DefaultRecorderFactory.class).in(Scopes.SINGLETON);

    }

    /**
     * 增强方式
     *
     * @return : void
     * @author 江浩
     */
    private void enhanceRule() {
        bind(EnhanceRule.class).annotatedWith(Names.named(EnhanceRule.Key.METHOD)).to(EnhanceMethodRule.class).in(Scopes.SINGLETON);
        bind(EnhanceRule.class).annotatedWith(Names.named(EnhanceRule.Key.CONSTRUCTOR)).to(EnhanceConstructorRule.class).in(Scopes.SINGLETON);
        bind(EnhanceRule.class).annotatedWith(Names.named(EnhanceRule.Key.STATIC_METHOD)).to(EnhanceStaticMethodRule.class).in(Scopes.SINGLETON);
        bind(EnhanceRule.class).annotatedWith(Names.named(EnhanceRule.Key.SOURCE)).to(EnhanceSourceRule.class).in(Scopes.SINGLETON);
    }

}
