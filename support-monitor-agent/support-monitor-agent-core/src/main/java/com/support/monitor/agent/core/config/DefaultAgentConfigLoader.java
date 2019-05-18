package com.support.monitor.agent.core.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

/**
 * 配置
 *
 * @author 江浩
 */
@Singleton
public class DefaultAgentConfigLoader implements ConfigLoader {

    private static final String DEFAULT_CONFIG_CLASSPATH = "agent.conf";

    private Config config;

    @Inject
    public DefaultAgentConfigLoader() {

    }

    @Override
    public Config load() {
        return this.config = ConfigFactory.load(DEFAULT_CONFIG_CLASSPATH);
    }

    @Override
    public Config load(String fullPath) {
        return this.config = ConfigFactory.parseFile(new File(fullPath));
    }


    public String getString(String key, String defaultValue) {
        try {
            return config.getString(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }


    public String getPluginDir() {
        return getString("minitor.agent.plugin.dir", null);
    }

}
