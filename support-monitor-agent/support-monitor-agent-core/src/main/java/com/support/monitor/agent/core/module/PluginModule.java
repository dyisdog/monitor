package com.support.monitor.agent.core.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.plugin.PluginDefine;
import com.support.monitor.agent.core.plugin.PluginLoader;
import com.support.monitor.agent.core.plugin.SpiPluginLoader;

/**
 * 插件加载模块
 *
 * @author 江浩
 */
public class PluginModule implements Module /**extends AbstactModule*/
{

    public static final String SPI = "Spi";


    @Override
    public void configure(Binder binder) {

        TypeLiteral<PluginLoader<PluginDefine>> pluginLoader = new TypeLiteral<PluginLoader<PluginDefine>>() {
        };

        binder.bind(pluginLoader).annotatedWith(Names.named(SPI)).to(SpiPluginLoader.class).in(Scopes.SINGLETON);
    }

}
