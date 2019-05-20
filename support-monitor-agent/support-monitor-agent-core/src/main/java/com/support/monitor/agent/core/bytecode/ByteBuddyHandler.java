package com.support.monitor.agent.core.bytecode;

import com.google.inject.Injector;
import com.support.monitor.agent.core.plugin.PluginDefine;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author 江浩
 */
public class ByteBuddyHandler implements ByteCodeHandler {

    private static final AgentBuilder agentBuilder = new AgentBuilder.Default(new ByteBuddy());

    @Override
    public void handle(Injector injector, Instrumentation instrumentation, Supplier<List<PluginDefine>> supplier) {


    }

//    private void definePluginHandler(PluginDefine pluginDefine) {
//
//        TargetInterceptPoint targetInterceptPoint = pluginDefine.targetInterceptPoint();
//        agentBuilder
//                .type(defaultClassMatcher(targetInterceptPoint))
//                .transform((builder, typeDescription, classLoader, module) -> builder.method(defaultMethodMatcher(targetInterceptPoint))
//                        .intercept(MethodDelegation
//                                .withDefaultConfiguration()
//                                // 覆写参数
//                                .withBinders(
//                                        Morph.Binder.install(OverrideCallable.class)
//                                )
//                                .to(new MethodsInterWithOverrideArgs(pluginDefine.aroundInterceptor()))
//                        ))
//                .installOn(instrumentation);
//
//    }
}
