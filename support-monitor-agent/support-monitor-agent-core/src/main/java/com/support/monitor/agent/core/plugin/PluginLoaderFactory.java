package com.support.monitor.agent.core.plugin;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.config.AgentConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 插件加载工厂
 *
 * @author 江浩
 */
@Slf4j
public class PluginLoaderFactory implements PluginLoader {

    private final Injector injector;
    private final PluginLoader pluginLoader;
    private AgentConfig agentConfig;

    public PluginLoaderFactory(Injector injector, AgentConfig agentConfig) {
        this.injector = injector;
        this.agentConfig = agentConfig;

        //plugin default use spi loader
        String loadType = agentConfig.getPluginLoadType();
        log.info("plugin loader type={}", loadType);
        this.pluginLoader = this.injector.getInstance(Key.get(PluginLoader.class, Names.named(loadType)));
    }

    @Override
    public List<PluginDefine> loadPlugin() {
        return this.pluginLoader.loadPlugin();
    }
}
