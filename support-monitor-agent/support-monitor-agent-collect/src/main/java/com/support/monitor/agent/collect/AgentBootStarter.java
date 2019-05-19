package com.support.monitor.agent.collect;

import com.google.inject.*;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.interceptor.TargetInterceptPoint;
import com.support.monitor.agent.core.module.ApplicationContextModuleFactory;
import com.support.monitor.agent.core.plugin.PluginDefine;
import com.support.monitor.agent.core.plugin.PluginLoader;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.commons.collections.CollectionUtils;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.Objects;

import static com.support.monitor.agent.core.module.PluginModule.SPI;

/**
 * 探针启动类
 *
 * @author 江浩
 */
@Slf4j
public class AgentBootStarter {


    private final ClassLoader parentClassLoader;

    private ApplicationContextModuleFactory applicationContextModuleFactory;

    private Instrumentation instrumentation;

    private AgentBuilder agentBuilder;

    private PluginLoader<PluginDefine> pluginLoader;

    private Injector injector;


    public AgentBootStarter(ClassLoader parentClassLoader,
                            ApplicationContextModuleFactory applicationContextModuleFactory,
                            Instrumentation instrumentation) {
        this.parentClassLoader = parentClassLoader;
        this.instrumentation = instrumentation;
        this.injector = Guice.createInjector(Stage.PRODUCTION, applicationContextModuleFactory.newModule());
        this.agentBuilder = this.injector.getInstance(AgentBuilder.class);
        //plugin ref loader
        this.pluginLoader = this.injector.getInstance(Key.get(new TypeLiteral<PluginLoader<PluginDefine>>() {
        }, Names.named(SPI)));
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

        TargetInterceptPoint targetInterceptPoint = pluginDefine.targetInterceptPoint();
        agentBuilder
                .type(defaultClassMatcher(targetInterceptPoint))
                .transform((builder, typeDescription, classLoader, module) -> builder.method(defaultMethodMatcher(targetInterceptPoint))
                        .intercept(MethodDelegation
                                .withDefaultConfiguration()
                                // 覆写参数
                                .withBinders(
                                        Morph.Binder.install(OverrideCallable.class)
                                )
                                .to(new MethodsInterWithOverrideArgs(pluginDefine.aroundInterceptor()))
                        ))
                .installOn(instrumentation);

    }


    private ElementMatcher<? super TypeDescription> defaultClassMatcher(TargetInterceptPoint targetInterceptPoint) {
        if (Objects.isNull(targetInterceptPoint)) {
            return ElementMatchers.none();
        }
        return targetInterceptPoint.classMatcher();
    }

    private ElementMatcher<? super MethodDescription> defaultMethodMatcher(TargetInterceptPoint targetInterceptPoint) {
        if (Objects.isNull(targetInterceptPoint)) {
            return ElementMatchers.none();
        }
        return targetInterceptPoint.methodMatcher();
    }


}
