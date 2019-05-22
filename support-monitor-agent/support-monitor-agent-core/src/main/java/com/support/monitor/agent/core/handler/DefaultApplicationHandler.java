package com.support.monitor.agent.core.handler;

import com.google.inject.Inject;
import com.support.monitor.agent.core.InstrumentDebuggingClass;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhanceFactory;
import com.support.monitor.agent.core.plugin.PluginDefine;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import org.apache.commons.collections.CollectionUtils;

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

    @Inject
    public DefaultApplicationHandler(
            AgentBuilder agentBuilder,
            AgentConfig config,
            Instrumentation instrumentation,
            EnhanceFactory enhanceFactory) {

        this.agentBuilder = agentBuilder;
        this.config = config;
        this.instrumentation = instrumentation;
        this.enhanceFactory = enhanceFactory;
    }

    @Override
    public void handle(List<PluginDefine> pluginDefines) {
        if (CollectionUtils.isEmpty(pluginDefines)) {
            log.info("plugin defines empty");
            return;
        }
        pluginDefines.forEach(pluginDefine -> {
            List<EnhanceContext> enhanceContexts = pluginDefine.enhanceContexts();
            ElementMatcher<? super TypeDescription> classDescription = pluginDefine.classDescription();
            log.info("加载: {}", classDescription.toString());

            this.agentBuilder.type(ElementMatchers.not(isInterface()).and(classDescription))
                    .transform((builder, typeDescription, classLoader, module) ->
                            enhanceFactory.enhance(builder, enhanceContexts))
                    .with(new Listener())
                    .installOn(this.instrumentation);
        });
    }

    private static class Listener implements AgentBuilder.Listener {
        @Override
        public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

        }

        @Override
        public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                                     boolean loaded, DynamicType dynamicType) {
            log.debug("On Transformation class {}.", typeDescription.getName());

            InstrumentDebuggingClass.INSTANCE.log(typeDescription, dynamicType);
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
