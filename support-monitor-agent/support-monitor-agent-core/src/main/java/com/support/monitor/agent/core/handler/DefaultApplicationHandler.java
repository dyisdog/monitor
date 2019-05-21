package com.support.monitor.agent.core.handler;

import com.google.inject.Inject;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.context.PluginSetupContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhanceFactory;
import com.support.monitor.agent.core.plugin.PluginDefine;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
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
            PluginSetupContext setupContext = pluginDefine.getPluginSetupContext();
            List<EnhanceContext> enhanceContexts = setupContext.delegations();
            StringBuilder sb = new StringBuilder();
            // thread stack error ?
            this.handle(this.instrumentation, enhanceContexts, 0, sb);
            log.info("plugin [{}] setting context: {} \t\t\t{}", setupContext.name(), Objects.isNull(enhanceContexts) ? 0 : enhanceContexts.size(), sb.toString());
        });
    }

    private void handle(Instrumentation instrumentation, List<EnhanceContext> enhanceContexts, int index, StringBuilder sb) {
        if (Objects.isNull(enhanceContexts) || index >= enhanceContexts.size()) {
            return;
        }
        EnhanceContext enhanceContext = enhanceContexts.get(index);

        ElementMatcher<? super TypeDescription> classInterceptor = enhanceContext.getClassDescription();
        ElementMatcher<? super MethodDescription> methodInterceptor = enhanceContext.getMethodDescription();
        sb.append("\n\t\t\thanding context[").append(index + 1).append("]:").append(enhanceContext.getTag())
                .append("\n\t\t\t:").append(classInterceptor)
                .append("\n\t\t\t:").append(methodInterceptor);

        //增强操作
        this.agentBuilder
                .type(classInterceptor)
                .transform((builder, typeDescription, classLoader, module) ->
                        enhanceFactory.enhance(builder, enhanceContext))
                .installOn(instrumentation);


//        MethodDelegation methodDelegation = enhanceFactory.methodsWithOverrideArgsDelegation(enhanceContext.getInterceptorClass());
//
//        this.agentBuilder
//                .type(classInterceptor)
//                .transform(new AgentBuilder.Transformer() {
//                    @Override
//                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
//
//                        System.out.println(builder + "============>>");
//                        return enhanceFactory.enhance(builder, enhanceContext);
//                        //return builder.method(methodInterceptor).intercept(methodDelegation);
//                    }
//                })
//                .installOn(instrumentation);


        //next plugin context setting
        this.handle(instrumentation, enhanceContexts, ++index, sb);
    }

}
