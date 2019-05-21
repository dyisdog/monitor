package com.support.monitor.agent.core.handler;

import com.google.inject.Inject;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.Delegation;
import com.support.monitor.agent.core.context.PluginSetupContext;
import com.support.monitor.agent.core.interceptor.delegation.MethodDelegationFactory;
import com.support.monitor.agent.core.plugin.PluginDefine;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.MethodDelegation;
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

    private MethodDelegationFactory methodDelegationFactory;

    @Inject
    public DefaultApplicationHandler(
            AgentBuilder agentBuilder,
            AgentConfig config,
            Instrumentation instrumentation,
            MethodDelegationFactory methodDelegationFactory) {

        this.agentBuilder = agentBuilder;
        this.config = config;
        this.instrumentation = instrumentation;
        this.methodDelegationFactory = methodDelegationFactory;
    }

    @Override
    public void handle(List<PluginDefine> pluginDefines) {
        if (CollectionUtils.isEmpty(pluginDefines)) {
            log.info("plugin defines empty");
            return;
        }

        pluginDefines.forEach(pluginDefine -> {
            PluginSetupContext setupContext = pluginDefine.getPluginSetupContext();
            List<Delegation> delegations = setupContext.delegations();
            StringBuilder sb = new StringBuilder();
            // thread stack error ?
            this.handle(this.instrumentation, delegations, 0, sb);
            log.info("plugin [{}] setting context: {} \t\t\t{}", setupContext.name(), Objects.isNull(delegations) ? 0 : delegations.size(), sb.toString());
        });
    }

    private void handle(Instrumentation instrumentation, List<Delegation> delegations, int index, StringBuilder sb) {
        if (Objects.isNull(delegations) || index >= delegations.size()) {
            return;
        }
        Delegation delegation = delegations.get(index);

        ElementMatcher<? super TypeDescription> classInterceptor = delegation.getClassDescription();
        ElementMatcher<? super MethodDescription> methodInterceptor = delegation.getMethodDescription();
        sb.append("\n\t\t\thanding context[").append(index + 1).append("]:").append(delegation.getTag())
                .append("\n\t\t\t:").append(classInterceptor)
                .append("\n\t\t\t:").append(methodInterceptor);
        //method
        //static
        //方法拦截委托
        MethodDelegation methodDelegation = methodDelegationFactory.defaultMethodDelegation(delegation.getInterceptorClass());

        this.agentBuilder
                .type(classInterceptor)
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.method(methodInterceptor).intercept(methodDelegation))
                .installOn(instrumentation);

        //next plugin context setting
        this.handle(instrumentation, delegations, ++index, sb);
    }

}
