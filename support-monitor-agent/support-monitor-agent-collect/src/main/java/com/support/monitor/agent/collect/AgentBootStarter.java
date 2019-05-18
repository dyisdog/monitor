package com.support.monitor.agent.collect;

import com.support.monitor.agent.collect.config.AgentConfig;
import com.support.monitor.agent.core.plugin.PluginDefine;
import com.support.monitor.agent.core.plugin.PluginLoader;
import com.support.monitor.agent.core.plugin.SpiPluginLoader;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Morph;
import org.apache.commons.collections.CollectionUtils;

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

    private final AgentConfig agentConfig;

    private Instrumentation instrumentation;

    private AgentBuilder agentBuilder = new AgentBuilder.Default(new ByteBuddy());

    private PluginLoader<PluginDefine> pluginLoader = new SpiPluginLoader();

    public AgentBootStarter(ClassLoader parentClassLoader,
                            AgentConfig agentConfig,
                            Instrumentation instrumentation) {
        this.parentClassLoader = parentClassLoader;
        this.agentConfig = agentConfig;
        this.instrumentation = instrumentation;
    }

    public boolean init() {

        this.preInit();

        return true;
    }

    private void preInit() {

        List<PluginDefine> pluginDefines = pluginLoader.loadPlugin();

        if (CollectionUtils.isNotEmpty(pluginDefines)) {
            log.info("插件数量[{}]", pluginDefines.size());
            pluginDefines.forEach(p -> {
                definePluginHandler(p);
            });
        }
    }

    private void definePluginHandler(PluginDefine pluginDefine) {

        agentBuilder
                .ignore(pluginDefine.ignoreMatcher())
                .type(pluginDefine.classMatcher())
                .transform((builder, typeDescription, classLoader, module) -> builder.method(pluginDefine.methodMatcher())
                        .intercept(MethodDelegation
                                .withDefaultConfiguration()
                                // 覆写参数
                                .withBinders(
                                        Morph.Binder.install(OverrideCallable.class)
                                )
                                .to(new MethodsInterWithOverrideArgs(pluginDefine.interceptor()))
                        ))
                .installOn(instrumentation);

    }


}
