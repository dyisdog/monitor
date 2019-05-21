package com.support.monitor.agent.core.context;

import com.google.common.collect.Lists;
import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import java.util.List;
import java.util.UUID;

/**
 * @author 江浩
 */
@Slf4j
public abstract class AbstractPluginSetupContext implements PluginSetupContext {


    private final List<EnhanceContext> enhanceContexts = Lists.newArrayList();

    public AbstractPluginSetupContext() {
        this.init();
    }

    /**
     * 插件执行绑定
     *
     * @param classDescription  :
     * @param methodDescription :
     * @param interceptorClass  :
     * @return : com.support.monitor.agent.core.context.AbstractPluginSetupContext
     * @author 江浩
     */
    public AbstractPluginSetupContext binder(ElementMatcher<? super TypeDescription> classDescription,
                                             ElementMatcher<? super MethodDescription> methodDescription,
                                             Class<? extends AroundInterceptor> interceptorClass) {
        return binder(UUID.randomUUID().toString(), classDescription, methodDescription, interceptorClass);
    }

    public AbstractPluginSetupContext binder(String tag, ElementMatcher<? super TypeDescription> classDescription,
                                             ElementMatcher<? super MethodDescription> methodDescription,
                                             Class<? extends AroundInterceptor> interceptorClass) {
        enhanceContexts.add(EnhanceContext.builder()
                .tag(tag)
                .classDescription(classDescription)
                .methodDescription(methodDescription)
                .interceptorClass(interceptorClass)
                .build());

        return this;
    }


    @Override
    public List<EnhanceContext> delegations() {
        return enhanceContexts;
    }

    /**
     * 初始执行
     *
     * @return : void
     * @author 江浩
     */
    public abstract void init();


}
