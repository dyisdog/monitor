package com.support.monitor.agent.core.plugin;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 江浩
 */
@Slf4j
@Singleton
public class SpiPluginLoader implements PluginLoader {

    @Override
    public List<PluginDefine> loadPlugin() {
        Collection<PluginDefine> defines = SpiPluginLoadHelper.loadFactories(PluginDefine.class, this.getClass().getClassLoader());
        return new ArrayList<>(defines);
    }


}
