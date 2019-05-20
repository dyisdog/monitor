package com.support.monitor.agent.core.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.support.monitor.agent.core.plugin.PluginLoader;
import com.support.monitor.agent.core.plugin.SpiPluginLoader;

/**
 * 插件加载模块
 *
 * @author 江浩
 */
public class PluginModule implements Module /**extends AbstactModule*/
{

    @Override
    public void configure(Binder binder) {

        binder.bind(PluginLoader.class).to(SpiPluginLoader.class).in(Scopes.SINGLETON);
    }

}
