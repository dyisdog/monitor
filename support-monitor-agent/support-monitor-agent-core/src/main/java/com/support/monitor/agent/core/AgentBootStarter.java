package com.support.monitor.agent.core;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.handler.ApplicationHandler;
import com.support.monitor.agent.core.plugin.PluginDefine;
import com.support.monitor.agent.core.plugin.PluginLoader;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;
import java.util.List;

/**
 * 探针启动类
 *
 * @author 江浩
 */
@Slf4j
public class AgentBootStarter {

    private final PluginLoader pluginLoader;
    private final ApplicationHandler applicationHandler;
    private Instrumentation instrumentation;
    private Injector injector;
    private AgentConfig agentConfig;

    @Inject
    public AgentBootStarter(AgentConfig agentConfig,
                            ApplicationHandler applicationHandler,
                            PluginLoader pluginLoader,
                            Instrumentation instrumentation,
                            Injector injector) {
        this.agentConfig = agentConfig;
        this.instrumentation = instrumentation;
        this.applicationHandler = applicationHandler;
        this.pluginLoader = pluginLoader;
        this.instrumentation = instrumentation;
        this.injector = injector;
        this.initAware();
    }

    /**
     * 初始绑定信息
     * <p>
     * {@link Injector} 注入也是一种方式
     * </p>
     */
    private void initAware() {
        this.pluginLoader.initAware(this.agentConfig, this.injector, this.instrumentation);
        this.applicationHandler.initAware(this.agentConfig, this.injector, this.instrumentation);
    }

    /**
     * 初始方法
     *
     * @return : boolean
     * @author 江浩
     */
    private void init() {
        List<PluginDefine> pluginDefines = this.pluginLoader.loadPlugin();
        this.applicationHandler.handle(pluginDefines);
    }

    public void start(String path) {
        this.agentConfig.load(path);
        this.init();
    }


}
