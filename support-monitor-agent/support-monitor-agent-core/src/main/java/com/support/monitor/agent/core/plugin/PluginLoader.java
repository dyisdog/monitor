package com.support.monitor.agent.core.plugin;

import java.util.List;

/**
 * 插件加载
 *
 * @author 江浩
 */
public interface PluginLoader {


    /**
     * 加载插件信息
     *
     * @return : java.util.List<P>
     * @author 江浩
     */
    default List<PluginDefine> loadPlugin() {
        return null;
    }
}
