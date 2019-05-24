package com.support.monitor.agent.core.handler;

import com.google.inject.Inject;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.debug.EnhanceDebugFactory;
import com.support.monitor.agent.core.interceptor.enhance.EnhanceFactory;
import com.support.monitor.agent.core.plugin.PluginDefine;
import com.support.monitor.agent.core.plugin.PluginLoader;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.isInterface;

/**
 * @author 江浩
 */
@Slf4j
public class DefaultApplicationHandler implements ApplicationHandler {

    private AgentBuilder agentBuilder;

    private AgentConfig config;

    private Instrumentation instrumentation;

    private EnhanceFactory enhanceFactory;

    private EnhanceDebugFactory enhanceDebugFactory;

    private PluginLoader pluginLoader;

    @Inject
    public DefaultApplicationHandler(
            AgentBuilder agentBuilder,
            AgentConfig config,
            Instrumentation instrumentation,
            EnhanceFactory enhanceFactory,
            EnhanceDebugFactory enhanceDebugFactory,
            PluginLoader pluginLoader) {

        this.agentBuilder = agentBuilder;
        this.config = config;
        this.instrumentation = instrumentation;
        this.enhanceFactory = enhanceFactory;
        this.enhanceDebugFactory = enhanceDebugFactory;
        this.pluginLoader = pluginLoader;
    }

    @Override
    public void handle() {

        //TODO

        this.handle(pluginLoader.loadPlugin(), 0);
    }

    private void handle(List<PluginDefine> loadPlugins, int index) {
        if (index >= loadPlugins.size()) {
            return;
        }
        PluginDefine pluginDefine = loadPlugins.get(index);
        log.info("加载插件: {}", pluginDefine.name());

        List<EnhanceContext> enhanceContexts = pluginDefine.enhanceContexts();
        ElementMatcher<? super TypeDescription> classDescription = ElementMatchers.not(isInterface()).and(pluginDefine.classDescription());

        this.agentBuilder.type(classDescription)
                .transform((builder, typeDescription, classLoader, module) -> enhanceFactory.enhance(builder, enhanceContexts))
                .with(new AgentEnhanceLister(this.enhanceDebugFactory))
                .installOn(this.instrumentation);

        this.handle(loadPlugins, ++index);
    }

    /**
     * 监听
     *
     * @author 江浩
     */
    private static class AgentEnhanceLister implements AgentBuilder.Listener {

        private EnhanceDebugFactory enhanceDebugFactory;

        public AgentEnhanceLister(EnhanceDebugFactory enhanceDebugFactory) {
            this.enhanceDebugFactory = enhanceDebugFactory;
        }

        @Override
        public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

        }

        @Override
        public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                                     boolean loaded, DynamicType dynamicType) {
            enhanceDebugFactory.fileWrite(typeDescription, dynamicType);
        }

        @Override
        public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                              boolean loaded) {

        }

        @Override
        public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded,
                            Throwable throwable) {
            log.error("Enhance class " + typeName + " error.", throwable);
        }

        @Override
        public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
        }
    }


}
