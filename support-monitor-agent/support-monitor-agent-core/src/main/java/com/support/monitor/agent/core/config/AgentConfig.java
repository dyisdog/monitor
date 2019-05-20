package com.support.monitor.agent.core.config;

import java.io.File;

public interface AgentConfig {

    void load(String path);


    void load(File file);

    void load();

    String getString(String key, String defaultValue);

    /**
     * 获取插件加载方式
     *
     * @return
     */
    String getPluginLoadType();

}
