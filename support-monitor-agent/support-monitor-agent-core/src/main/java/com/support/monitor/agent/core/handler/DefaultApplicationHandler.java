package com.support.monitor.agent.core.handler;

import com.google.inject.Inject;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.PluginSetupContext;
import com.support.monitor.agent.core.interceptor.MethodsInterWithOverrideArgs;
import com.support.monitor.agent.core.interceptor.OverrideCallable;
import com.support.monitor.agent.core.interceptor.PluginInterceptor;
import com.support.monitor.agent.core.module.provider.ObjectBinderFactory;
import com.support.monitor.agent.core.plugin.PluginDefine;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.commons.collections.CollectionUtils;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.Objects;

/**
 * @author 江浩
 */
@Slf4j
public class DefaultApplicationHandler implements ApplicationHandler {

    private AgentBuilder agentBuilder;

    private AgentConfig config;

    private Instrumentation instrumentation;

    private ObjectBinderFactory objectBinderFactory;

    @Inject
    public DefaultApplicationHandler(
            AgentBuilder agentBuilder,
            AgentConfig config,
            ObjectBinderFactory objectBinderFactory,
            Instrumentation instrumentation) {
        this.agentBuilder = agentBuilder;
        this.config = config;
        this.objectBinderFactory = objectBinderFactory;
        this.instrumentation = instrumentation;
    }

    @Override
    public void handle(List<PluginDefine> pluginDefines) {
        if (CollectionUtils.isEmpty(pluginDefines)) {
            log.info("plugin defines empty");
            return;
        }

        pluginDefines.forEach(pluginDefine -> {
            PluginSetupContext setupContext = pluginDefine.getPluginSetupContext();
            List<PluginInterceptor> pluginInterceptors = setupContext.interceptors();
            StringBuilder sb = new StringBuilder();
            // thread stack error ?
            this.handle(this.instrumentation, pluginInterceptors, 0, sb);
            log.info("plugin [{}] setting context: {} \t\t\t{}", setupContext.getPluginName(), Objects.isNull(pluginInterceptors) ? 0 : pluginInterceptors.size(), sb.toString());
        });
    }

    private void handle(Instrumentation instrumentation, List<PluginInterceptor> pluginInterceptors, int index, StringBuilder sb) {
        if (Objects.isNull(pluginInterceptors) || index >= pluginInterceptors.size()) {
            return;
        }
        PluginInterceptor pluginInterceptor = pluginInterceptors.get(index);

        ElementMatcher<? super TypeDescription> classInterceptor = pluginInterceptor.classInterceptor();
        ElementMatcher<? super MethodDescription> methodInterceptor = pluginInterceptor.methodInterceptor();
        sb.append("\n\t\t\thanding context[").append(index + 1).append("]:").append(pluginInterceptor.name())
                .append("\n\t\t\t:").append(classInterceptor)
                .append("\n\t\t\t:").append(methodInterceptor);
        //method
        //static
        //这里需要判定多种方式
        this.agentBuilder
                .type(classInterceptor)
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.method(methodInterceptor)
                                .intercept(MethodDelegation
                                        .withDefaultConfiguration()
                                        // 覆写参数
                                        .withBinders(
                                                Morph.Binder.install(OverrideCallable.class)
                                        )
                                        .to(new MethodsInterWithOverrideArgs(pluginInterceptor))
                                ))
                .installOn(instrumentation);

        //next plugin context setting
        this.handle(instrumentation, pluginInterceptors, ++index, sb);
    }

}
