package com.support.monitor.agent.core.config;

import com.google.common.collect.Lists;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 配置加载器
 *
 * @author 江浩
 */
@Getter
public class ConfigLoader {

    public static final String DEFAULT_CONFIG_PATH = "agent.conf";

    private static final String SPLIT_CONFIG = ",";

    private final Config config;

    public ConfigLoader() {
        this(DEFAULT_CONFIG_PATH);
    }

    public ConfigLoader(String path) {
        if (StringUtils.isBlank(path) || StringUtils.equalsIgnoreCase(DEFAULT_CONFIG_PATH, path)) {
            this.config = ConfigFactory.load(path);
        } else {
            this.config = ConfigFactory.parseFile(new File(path));
        }
    }

    public List<String> getStringList(String key, List<String> defaultValue) {
        try {
            return Arrays.asList(StringUtils.split(config.getString(key), SPLIT_CONFIG));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public List<String> getStringList(String key) {
        return getStringList(key, Lists.newArrayList());
    }

    public String getString(String key, String defaultValue) {
        try {
            return config.getString(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        try {
            return config.getBoolean(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String ignore() {
        return getString("agent.ignore");
    }

    public String type() {
        return getString("agent.type");
    }

    public String method() {
        return getString("agent.method");
    }


}
