package com.support.monitor.agent.collect;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.support.monitor.agent.core.bytecode.ByteCodeHandler;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.module.ApplicationContextModuleFactory;
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


    private final ClassLoader parentClassLoader;

    private final PluginLoader pluginLoader;
    private final ByteCodeHandler byteCodeHandler;
    private Instrumentation instrumentation;
    private Injector injector;
    private AgentConfig agentConfig;

    public AgentBootStarter(String agentArgs, ClassLoader parentClassLoader,
                            ApplicationContextModuleFactory applicationContextModuleFactory,
                            Instrumentation instrumentation) {
        this.parentClassLoader = parentClassLoader;

        this.injector = Guice.createInjector(Stage.PRODUCTION, applicationContextModuleFactory.newModule());
        this.instrumentation = instrumentation;
        this.pluginLoader = this.injector.getInstance(PluginLoader.class);
        this.byteCodeHandler = this.injector.getInstance(ByteCodeHandler.class);

        this.agentConfig = this.injector.getInstance(AgentConfig.class);
        agentConfig.load(agentArgs);
    }

    /**
     * 初始绑定信息
     */
    private void initAware() {
        this.pluginLoader.initAware(this.agentConfig, this.injector, this.instrumentation);
        this.byteCodeHandler.initAware(this.agentConfig, this.injector, this.instrumentation);
    }

    /**
     * 初始方法
     *
     * @return : boolean
     * @author 江浩
     */
    public boolean init() {

        this.initAware();

        List<PluginDefine> pluginDefines = this.pluginLoader.loadPlugin();
        this.byteCodeHandler.handle(pluginDefines);

        return true;
    }


}
