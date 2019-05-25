package com.support.monitor.agent.core.plugin;

import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.interceptor.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.MethodAroundInterceptor;
import com.support.monitor.agent.core.interceptor.StaticMethodAroundInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 江浩
 */
public abstract class AbstractPluginDefine implements PluginDefine {

    private List<EnhanceContext> enhanceContexts = new ArrayList<>();

    private ElementMatcher<? super TypeDescription> classDescription;

    private String name;

    public AbstractPluginDefine() {
        this.init();
    }

    /**
     * 初始方法
     */
    public abstract void init();

    private void binder(EnhanceContext enhanceContext) {
        if (!enhanceContexts.contains(enhanceContext)) {
            enhanceContexts.add(enhanceContext);
        }
    }

    @Override
    public ElementMatcher<? super TypeDescription> classDescription() {
        return this.classDescription;
    }


    @Override
    public List<EnhanceContext> enhanceContexts() {
        return enhanceContexts;
    }


    @Override
    public String name() {
        return StringUtils.isBlank(this.name) ? UUID.randomUUID().toString() : this.name;
    }

    public void pointName(String name) {
        this.name = name;
    }

    public void pointClass(ElementMatcher<? super TypeDescription> classDescription) {
        this.classDescription = classDescription;
    }

    /**
     * @param methodDescription :
     * @param interceptorClass  :
     * @return : com.support.monitor.agent.core.plugin.AbstractPluginDefine
     * @author 江浩
     */
    public <R extends MethodAroundInterceptor> void pointMethod(ElementMatcher<? super MethodDescription> methodDescription, Class<R> interceptorClass) {
        pointSetting(methodDescription, interceptorClass.getName());
    }

    public <R extends StaticMethodAroundInterceptor> void pointStaticMethod(ElementMatcher<? super MethodDescription> methodDescription, Class<R> interceptorClass) {
        pointSetting(methodDescription, interceptorClass.getName());
    }

    public <R extends ConstructorInterceptor> void pointConstructor(ElementMatcher<? super MethodDescription> methodDescription, Class<R> interceptorClass) {
        pointSetting(methodDescription, interceptorClass.getName());
    }

    private void pointSetting(ElementMatcher<? super MethodDescription> methodDescription, String name) {
        EnhanceContext enhanceContext = new EnhanceContext();
        enhanceContext.setMethodDescription(methodDescription);
        enhanceContext.setInterceptorClassName(name);
        binder(enhanceContext);
    }

}
