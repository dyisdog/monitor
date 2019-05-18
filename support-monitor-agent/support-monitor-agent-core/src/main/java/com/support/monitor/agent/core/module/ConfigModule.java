package com.support.monitor.agent.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.support.monitor.agent.core.config.ConfigLoader;
import com.support.monitor.agent.core.config.DefaultAgentConfigLoader;

/**
 * 配置模块
 *
 * @author 江浩
 */
public class ConfigModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(ConfigLoader.class).to(DefaultAgentConfigLoader.class).in(Scopes.SINGLETON);
    }
}
