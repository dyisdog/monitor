package com.support.monitor.agent.core.plugin;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.exception.NotFoundPluginLoader;
import com.support.monitor.agent.core.plugin.adaptor.PluginLoaderAdaptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 江浩
 */
@Slf4j
@Singleton
public class DefaultPluginLoader implements PluginLoader {

    private AgentConfig config;

    private Injector injector;

    @Override
    public void initAware(AgentConfig config, Injector injector, Instrumentation instrumentation) {
        this.config = config;
        this.injector = injector;
    }

    @Override
    public List<PluginDefine> loadPlugin() {
        PluginLoaderAdaptor pluginLoaderAdaptor = null;
        try {
            pluginLoaderAdaptor = injector.getInstance(Key.get(PluginLoaderAdaptor.class, Names.named(this.config.getPluginLoader())));
        } catch (Exception e) {
            log.error("plugin adaptor not found: {}", this.config.getPluginLoader());
            throw new NotFoundPluginLoader(String.format("%s:%s", "pluginLoaderAdaptor", this.config.getPluginLoader()));
        }
        Collection<PluginDefine> defines = pluginLoaderAdaptor.loadFactory(PluginDefine.class, this.getClass().getClassLoader());
        return new ArrayList<>(defines);
    }


}
