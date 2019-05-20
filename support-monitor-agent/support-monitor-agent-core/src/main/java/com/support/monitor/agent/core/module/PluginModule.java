package com.support.monitor.agent.core.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.plugin.DefaultPluginLoader;
import com.support.monitor.agent.core.plugin.PluginLoader;
import com.support.monitor.agent.core.plugin.adaptor.PluginLoaderAdaptor;
import com.support.monitor.agent.core.plugin.adaptor.SpiPluginLoadAdaptor;

/**
 * 插件加载模块
 *
 * @author 江浩
 */
public class PluginModule implements Module /**extends AbstactModule*/
{

    public static final String SPI_LOADER_ADAPTOR = "spi";

    @Override
    public void configure(Binder binder) {

        binder.bind(PluginLoader.class).to(DefaultPluginLoader.class).in(Scopes.SINGLETON);
        binder.bind(PluginLoaderAdaptor.class).annotatedWith(Names.named(SPI_LOADER_ADAPTOR)).to(SpiPluginLoadAdaptor.class).in(Scopes.SINGLETON);

    }

}
