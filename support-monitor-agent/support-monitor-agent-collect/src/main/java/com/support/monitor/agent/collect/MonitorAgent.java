package com.support.monitor.agent.collect;

import com.support.monitor.agent.core.config.ConfigLoader;
import com.support.monitor.agent.core.matcher.IJunctionLoader;
import com.support.monitor.agent.core.matcher.JunctionLoaderFactory;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.utility.JavaModule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * 数据采集agent
 *
 * @author 江浩
 */
public class MonitorAgent {

    private static final Logger LOG = LoggerFactory.getLogger(MonitorAgent.class);

    private static String agentConfPath(String agentArgs) {
        return StringUtils.isNotBlank(agentArgs) ? agentArgs : ConfigLoader.DEFAULT_CONFIG_PATH;
    }

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        LOG.debug(">>>>> monitor agent collect <<<<<");
        final ByteBuddy byteBuddy = new ByteBuddy();
        final IJunctionLoader junctionLoader = JunctionLoaderFactory.builder(agentConfPath(agentArgs));

        new AgentBuilder.Default(byteBuddy)
                .ignore(junctionLoader.ignoreJunction())
                .type(junctionLoader.typeJunction())
                .transform(new AgentTransform(junctionLoader.methodJunction()))
                .with(new AgentListener())
                .installOn(instrumentation);
    }


    /**
     * @author 江浩
     */
    private static class AgentTransform implements AgentBuilder.Transformer {

        private ElementMatcher.Junction<? super MethodDescription> methodJunction;

        public AgentTransform(ElementMatcher.Junction<? super MethodDescription> methodJunction) {
            this.methodJunction = methodJunction;
        }

        @Override
        public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {

            return builder.method(methodJunction)
                    .intercept(MethodDelegation
                            .to(AgentMethodInterceptor.class)
                            .andThen(SuperMethodCall.INSTANCE)
                    );
        }
    }

    /**
     * @author 江浩
     */
    private static class AgentListener implements AgentBuilder.Listener {
        @Override
        public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

        }

        @Override
        public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                                     boolean loaded, DynamicType dynamicType) {
            LOG.info("onTransformation class {}.", typeDescription.getName());
            //TODO 加载类信息
        }

        @Override
        public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                              boolean loaded) {
        }

        @Override
        public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded,
                            Throwable throwable) {
            LOG.error("Enhance class " + typeName + " error.", throwable);
        }

        @Override
        public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
        }
    }

}
