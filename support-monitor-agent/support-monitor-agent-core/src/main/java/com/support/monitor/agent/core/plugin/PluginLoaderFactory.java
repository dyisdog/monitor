package com.support.monitor.agent.core.plugin;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.config.AgentConfig;

import java.lang.instrument.Instrumentation;
import java.util.List;

/**
 * 插件加载工厂
 *
 * @author 江浩
 */
public class PluginLoaderFactory implements PluginLoader {

    private final Injector injector;
    private final Instrumentation instrumentation;
    private final PluginLoader pluginLoader;
    private AgentConfig agentConfig;

    public PluginLoaderFactory(Injector injector, AgentConfig agentConfig, Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
        this.injector = injector;
        this.agentConfig = agentConfig;

        //plugin default use spi loader
        this.pluginLoader = this.injector.getInstance(Key.get(PluginLoader.class, Names.named(agentConfig.getPluginLoadType())));
    }

    @Override
    public List<PluginDefine> loadPlugin() {
        return this.pluginLoader.loadPlugin();
    }
}
