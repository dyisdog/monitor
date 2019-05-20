package com.support.monitor.agent.collect;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.support.monitor.agent.core.bytecode.ByteCodeHandlerFactory;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.module.ApplicationContextModuleFactory;
import com.support.monitor.agent.core.plugin.PluginLoaderFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;

/**
 * 探针启动类
 *
 * @author 江浩
 */
@Slf4j
public class AgentBootStarter {


    private final ClassLoader parentClassLoader;

    private final PluginLoaderFactory pluginLoaderFactory;
    private final ByteCodeHandlerFactory byteCodeHandlerFactory;
    private Instrumentation instrumentation;
    private Injector injector;

    private String agentArgs;

    public AgentBootStarter(String agentArgs, ClassLoader parentClassLoader,
                            ApplicationContextModuleFactory applicationContextModuleFactory,
                            Instrumentation instrumentation) {
        this.agentArgs = agentArgs;
        this.parentClassLoader = parentClassLoader;

        this.injector = Guice.createInjector(Stage.PRODUCTION, applicationContextModuleFactory.newModule());
        this.instrumentation = instrumentation;
        //loading config
        AgentConfig agentConfig = this.injector.getInstance(AgentConfig.class);
        agentConfig.load(agentArgs);

        // instrumentation module ?
        //init factory
        this.pluginLoaderFactory = new PluginLoaderFactory(this.injector, agentConfig);
        this.byteCodeHandlerFactory = new ByteCodeHandlerFactory(this.injector, agentConfig);
    }

    public boolean init() {

        this.byteCodeHandlerFactory.handle(this.instrumentation, () -> {
            return this.pluginLoaderFactory.loadPlugin();
        });

        return true;
    }


}
