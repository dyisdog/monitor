package com.support.monitor.agent.core.bytecode;

import com.google.inject.Injector;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.PluginContext;
import com.support.monitor.agent.core.context.PluginSetupContext;
import com.support.monitor.agent.core.interceptor.MethodsInterWithOverrideArgs;
import com.support.monitor.agent.core.interceptor.OverrideCallable;
import com.support.monitor.agent.core.plugin.PluginDefine;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Morph;
import org.apache.commons.collections.CollectionUtils;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.Objects;

/**
 * @author 江浩
 */
@Slf4j
public class ByteBuddyHandler implements ByteCodeHandler {

    private AgentBuilder agentBuilder;

    private AgentConfig config;

    private Injector injector;

    private Instrumentation instrumentation;

    @Override
    public void initAware(AgentConfig config, Injector injector, Instrumentation instrumentation) {
        this.config = config;
        this.injector = injector;
        this.agentBuilder = Objects.isNull(injector) ? null : this.injector.getInstance(AgentBuilder.class);
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
            List<PluginContext> pluginContexts = setupContext.getPluginContexts();
            log.info("plugin [{}] setting context: {}", setupContext.getPluginName(), Objects.isNull(pluginContexts) ? 0 : pluginContexts.size());
            this.handle(this.instrumentation, pluginContexts, 0);
        });
    }

    private void handle(Instrumentation instrumentation, List<PluginContext> pluginContexts, int index) {
        if (index >= pluginContexts.size()) {
            return;
        }
        PluginContext pluginContext = pluginContexts.get(index);

        log.info("\n\t\t\thanding context :{}\n\t\t\t{}\n\t\t\t{}", pluginContext.tag(), pluginContext.classMatcher(), pluginContext.methodMatcher());

        //method
        //static
        //这里需要判定多种方式
        this.agentBuilder
                .type(pluginContext.classMatcher())
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.method(pluginContext.methodMatcher())
                                .intercept(MethodDelegation
                                        .withDefaultConfiguration()
                                        // 覆写参数
                                        .withBinders(
                                                Morph.Binder.install(OverrideCallable.class)
                                        )
                                        .to(new MethodsInterWithOverrideArgs(pluginContext.aroundInterceptor()))
                                ))
                .installOn(instrumentation);

        //next plugin context setting
        this.handle(instrumentation, pluginContexts, ++index);
    }

}
