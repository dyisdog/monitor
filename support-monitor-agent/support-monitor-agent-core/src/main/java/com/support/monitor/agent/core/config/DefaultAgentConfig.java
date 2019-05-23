package com.support.monitor.agent.core.config;

import com.google.inject.Singleton;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.util.Objects;

/**
 * 配置
 *
 * @author 江浩
 */
@Singleton
public class DefaultAgentConfig implements AgentConfig {

    private static final String DEFAULT_CONFIG_CLASSPATH = "agent.conf";

    private Config config;

    public DefaultAgentConfig(String args) {
        this.load(args);
    }

    @Override
    public void load() {
        this.config = ConfigFactory.load(DEFAULT_CONFIG_CLASSPATH);
    }

    @Override
    public void load(String path) {
        load(new File(path));
    }

    @Override
    public void load(File file) {
        if (Objects.isNull(file) || !file
                .exists()) {
            this.load();
            return;
        }
        this.config = ConfigFactory.parseFile(file);
    }

    @Override
    public String getString(String key, String defaultValue) {
        try {
            return config.getString(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public String getPluginLoader() {
        return this.getString("agent.plugin.loader.name", "spi");
    }

    @Override
    public String getDebugPath() {
        return this.getString("agent.plugin.debug.path", "");
    }
}
