package com.support.monitor.agent.collect.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 配置
 *
 * @author 江浩
 */
public class AgentConfig {

    private static final String DEFAULT_CONFIG_CLASSPATH = "agent.conf";

    private Config config;

    public AgentConfig(String fullPath) {
        if (StringUtils.isNotBlank(fullPath)) {
            this.config = ConfigFactory.parseFile(new File(fullPath));
        } else {
            this.config = ConfigFactory.load(DEFAULT_CONFIG_CLASSPATH);
        }

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
