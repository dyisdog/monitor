package com.support.monitor.agent.core.plugin.adaptor;

import java.util.Collection;

/**
 * 插件加载适配器
 *
 * @author
 */
public interface PluginLoaderAdaptor {

    default <T> T loadFactory(Class<T> clazz) {
        return null;
    }

    default <T> Collection<T> loadFactory(Class<T> clazz, ClassLoader classLoader) {
        return null;
    }
}
