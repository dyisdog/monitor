package com.support.monitor.agent.core.module;

import com.google.inject.*;
import com.google.inject.util.Modules;
import com.support.monitor.agent.core.AgentBootStarter;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * core 模块核心模块管理
 *
 * @author 江浩
 */
public class ApplicationContextModuleFactory implements ModuleFactory {

    private Set<Module> refModules = new HashSet<>();

    private Injector injector;

    public ApplicationContextModuleFactory add(Module... modules) {
        refModules.addAll(Arrays.asList(modules));
        return this;
    }

    @Override
    public Module initModule() {
        //default modules
        final Module init = new InitModule();
        final Module byteCode = new ByteCodeModule();
        final Module config = new ConfigModule();
        final Module plugin = new PluginModule();
        final TraceModule trace = new TraceModule();

        add(init, byteCode, config, plugin, trace);

        return Modules.combine(refModules);
    }

    @Override
    public void load(String args, Instrumentation instrumentation) {

        // add module
        add(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Instrumentation.class).toInstance(instrumentation);
            }
        });

        //setting module
        initModule();

        this.injector = Guice.createInjector(Stage.PRODUCTION, initModule());
        AgentBootStarter agentBootStarter = this.injector.getInstance(AgentBootStarter.class);
        agentBootStarter.start(args);
    }

}
