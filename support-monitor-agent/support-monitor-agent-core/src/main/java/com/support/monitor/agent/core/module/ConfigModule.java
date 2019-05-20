package com.support.monitor.agent.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.config.DefaultAgentConfig;

/**
 * 配置模块
 *
 * @author 江浩
 */
public class ConfigModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(AgentConfig.class).to(DefaultAgentConfig.class).in(Scopes.SINGLETON);
    }
}
