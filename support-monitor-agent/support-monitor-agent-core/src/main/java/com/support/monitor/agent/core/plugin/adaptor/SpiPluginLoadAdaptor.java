package com.support.monitor.agent.core.plugin.adaptor;

import java.util.*;

/**
 * spi 加载工具
 *
 * @author 江浩
 */
public class SpiPluginLoadAdaptor implements PluginLoaderAdaptor {

    @Override
    public <T> T loadFactory(Class<T> clazz) {
        T factory = loadFactoryOrNull(clazz);
        if (factory == null) {
            throw new IllegalStateException("Cannot find META-INF/services/" + clazz.getName() + " on classpath");
        } else {
            return factory;
        }
    }

    @Override
    public <T> Collection<T> loadFactory(Class<T> clazz, ClassLoader classLoader) {
        return this.loadFactories(clazz, classLoader);
    }

    private <T> T loadFactoryOrNull(Class<T> clazz) {
        Collection<T> collection = loadFactories(clazz);
        return !collection.isEmpty() ? collection.iterator().next() : null;
    }

    private <T> Collection<T> loadFactories(Class<T> clazz) {
        return loadFactories(clazz, null);
    }

    private <T> Collection<T> loadFactories(Class<T> clazz, ClassLoader classLoader) {
        List<T> list = new ArrayList<T>();
        ServiceLoader<T> factories;
        if (classLoader != null) {
            factories = ServiceLoader.load(clazz, classLoader);
        } else {
            factories = ServiceLoader.load(clazz);
        }

        if (factories.iterator().hasNext()) {
            factories.iterator().forEachRemaining(list::add);
            return list;
        } else {
            factories = ServiceLoader.load(clazz, SpiPluginLoadAdaptor.class.getClassLoader());
            if (factories.iterator().hasNext()) {
                factories.iterator().forEachRemaining(list::add);
                return list;
            } else {
                return Collections.emptyList();
            }
        }
    }
}
