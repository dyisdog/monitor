package com.support.monitor.agent.core.module;

import com.alipay.common.tracer.core.SofaTracer;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.AgentBootStarter;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.Reporter;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.debug.EnhanceDebugFactory;
import com.support.monitor.agent.core.handler.ApplicationHandler;
import com.support.monitor.agent.core.handler.DefaultApplicationHandler;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;
import com.support.monitor.agent.core.interceptor.enhance.EnhanceFactory;
import com.support.monitor.agent.core.interceptor.enhance.rule.EnhanceConstructorRule;
import com.support.monitor.agent.core.interceptor.enhance.rule.EnhanceMethodRule;
import com.support.monitor.agent.core.interceptor.enhance.rule.EnhanceRule;
import com.support.monitor.agent.core.interceptor.enhance.rule.EnhanceStaticMethodRule;
import com.support.monitor.agent.core.module.provider.*;
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


    @Override
    protected void configure() {

        initBind();

        providerBind();

        enhanceRule();

    }

    private void initBind() {
        bind(AgentBootStarter.class).in(Scopes.SINGLETON);
        bind(AgentConfig.class).toProvider(AgentConfigProvider.class).in(Scopes.SINGLETON);
        bind(AgentBuilder.class).to(AgentBuilder.Default.class).in(Scopes.SINGLETON);
        bind(ApplicationHandler.class).to(DefaultApplicationHandler.class).in(Scopes.SINGLETON);
    }


    private void providerBind() {
//        bind(TraceContext.class).toProvider(TraceContextProvider.class).in(Scopes.SINGLETON);
        //tracer
        bind(SofaTracer.class).toProvider(SofaTracerProvider.class).in(Scopes.SINGLETON);

        bind(Reporter.class).toProvider(ServerReporterProvider.class).in(Scopes.SINGLETON);
        bind(TraceContext.class).toProvider(TraceContextProvider.class).in(Scopes.SINGLETON);

        bind(EnhanceFactory.class).toProvider(EnhanceFactoryProvider.class).in(Scopes.SINGLETON);
        bind(EnhanceDebugFactory.class).toProvider(EnhanceDebugFactoryProvider.class).in(Scopes.SINGLETON);
        bind(InterceptorFactory.class).toProvider(InterceptorProvider.class).in(Scopes.SINGLETON);
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
        //bind(EnhanceRule.class).annotatedWith(Names.named(EnhanceRule.Key.SOURCE)).to(EnhanceSourceRule.class).in(Scopes.SINGLETON);
    }

}
