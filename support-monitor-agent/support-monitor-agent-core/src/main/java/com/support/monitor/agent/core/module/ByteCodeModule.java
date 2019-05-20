package com.support.monitor.agent.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.support.monitor.agent.core.handler.ApplicationHandler;
import com.support.monitor.agent.core.handler.DefaultApplicationHandler;
import net.bytebuddy.agent.builder.AgentBuilder;

/**
 * byte处理
 *
 * @author 江浩
 */
public class ByteCodeModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AgentBuilder.class).to(AgentBuilder.Default.class).in(Scopes.SINGLETON);
        bind(ApplicationHandler.class).to(DefaultApplicationHandler.class).in(Scopes.SINGLETON);
    }
}
