package com.support.monitor.agent.core.config;

import java.io.File;

/**
 * 配置信息
 *
 * @author 江浩
 */
public interface AgentConfig {

    /**
     * 根据绝对路径加载
     *
     * @param path :
     * @return : void
     * @author 江浩
     */
    void load(String path);

    /**
     * 根据文件加载
     *
     * @param file :
     * @return : void
     * @author 江浩
     */
    void load(File file);

    /**
     * 默认class path 加载
     *
     * @return : void
     * @author 江浩
     */
    void load();

    /**
     * 获取string 类型，不存在返回默认值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    String getString(String key, String defaultValue);

    /**
     * 获取插件加载方式
     *
     * @return
     */
    String getPluginLoadType();

    /**
     * 获取字节码处理方式
     *
     * @return
     */
    String getByteCodeType();
}
