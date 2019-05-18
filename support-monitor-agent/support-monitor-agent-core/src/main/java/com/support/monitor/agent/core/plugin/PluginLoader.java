package com.support.monitor.agent.core.plugin;

import java.util.List;

/**
 * 插件加载
 *
 * @author 江浩
 */
public interface PluginLoader<P> {


    /**
     * 加载插件信息
     *
     * @return : java.util.List<P>
     * @author 江浩
     */
    default List<P> loadPlugin() {
        return null;
    }
}
