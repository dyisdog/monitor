package com.monitor.agent.core.test;

import com.google.inject.*;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.module.ApplicationContextModuleFactory;
import com.support.monitor.agent.core.plugin.PluginDefine;
import com.support.monitor.agent.core.plugin.PluginLoader;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Before;
import org.junit.Test;

import static com.support.monitor.agent.core.module.PluginModule.SPI;


public class ApplicationContextModuleFactoryTest {

    private Injector injector;

    private PluginLoader<PluginDefine> definePluginLoader;


    @Before
    public void init() {
        Module module = new ApplicationContextModuleFactory().newModule();
        this.injector = Guice.createInjector(Stage.PRODUCTION, module);
    }

    @Test
    public void moduleFactoryTest() {
        System.out.println(this.injector.getInstance(AgentBuilder.class));

        TypeLiteral<PluginLoader<PluginDefine>> loaderType = new TypeLiteral<PluginLoader<PluginDefine>>() {
        };

        definePluginLoader = this.injector.getInstance(Key.get(loaderType, Names.named(SPI)));
        //int in = this.injector.getInstance().get();
        System.out.println(definePluginLoader);
    }


}
