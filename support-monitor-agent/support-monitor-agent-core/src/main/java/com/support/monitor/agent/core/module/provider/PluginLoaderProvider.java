package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.plugin.DefaultPluginLoader;
import com.support.monitor.agent.core.plugin.PluginLoader;

/**
 * @author 江浩
 */
public class PluginLoaderProvider implements Provider<PluginLoader> {

    private AgentConfig config;

    private Injector injector;

    @Inject
    public PluginLoaderProvider(AgentConfig config, Injector injector) {
        this.config = config;
        this.injector = injector;
    }

    @Override
    public PluginLoader get() {
        return new DefaultPluginLoader(this.config, this.injector);
    }
}
