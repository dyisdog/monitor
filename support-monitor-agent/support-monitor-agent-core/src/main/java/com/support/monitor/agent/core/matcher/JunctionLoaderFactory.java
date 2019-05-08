package com.support.monitor.agent.core.matcher;

/**
 * 连接器加载工厂
 *
 * @author 江浩
 */
public class JunctionLoaderFactory {

    /**
     * 构建所有的匹配器加载器
     *
     * @param path :
     * @return : com.support.monitor.agent.core.matcher.IJunctionLoader
     * @author 江浩
     */
    public static IJunctionLoader builder(String path) {
        return new PluginJunctionLoader(
                new ConfigFileJunctionLoader(path,
                        new DefaultTypeJunctionLoader()
                )
        );
    }

}
