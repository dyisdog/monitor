package com.support.monitor.commons.binder;

import com.typesafe.config.Config;

/**
 * @author 江浩
 */
public class ConfigHandler {

    private Config config;

    private ConfigHandler(Config config) {
        this.config = config;
    }

    public static ConfigHandler builder(Config config) {
        return new ConfigHandler(config);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        try {
            return config.getBoolean(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getString(String key, String defaultValue) {
        try {
            return this.config.getString(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public Number getNumber(String key, Number defaultValue) {
        try {
            return this.config.getNumber(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public Integer getInt(String key, Integer defaultValue) {
        try {
            return this.config.getInt(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }


}
