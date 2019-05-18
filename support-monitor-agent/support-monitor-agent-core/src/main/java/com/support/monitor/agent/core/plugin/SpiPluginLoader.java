package com.support.monitor.agent.core.plugin;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author 江浩
 */
@Slf4j
public class SpiPluginLoader implements PluginLoader<PluginDefine> {

    @Override
    public List<PluginDefine> loadPlugin() {
        Collection<PluginDefine> defines = SpiPluginLoadHelper.loadFactories(PluginDefine.class, this.getClass().getClassLoader());
        log.info("load plugin pluginDefine {} ", Objects.isNull(defines) ? 0 : defines.size());
        return new ArrayList<>(defines);
    }


}
