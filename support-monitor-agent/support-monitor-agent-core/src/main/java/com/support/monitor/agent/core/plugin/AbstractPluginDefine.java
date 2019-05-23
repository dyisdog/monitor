package com.support.monitor.agent.core.plugin;

import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.interceptor.ConstructorInterceptPoint;
import com.support.monitor.agent.core.interceptor.InterceptPoint;
import com.support.monitor.agent.core.interceptor.MethodsInterceptPoint;
import com.support.monitor.agent.core.interceptor.StaticMethodsInterceptPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 江浩
 */
public abstract class AbstractPluginDefine implements PluginDefine {

    private List<EnhanceContext> enhanceContexts = new ArrayList<>();

    public AbstractPluginDefine() {
        this.init();
    }

    /**
     * 初始方法
     */
    public abstract void init();

    public <P extends InterceptPoint> void binder(EnhanceContext<P> enhanceContext) {
        if (!enhanceContexts.contains(enhanceContext)) {
            enhanceContexts.add(enhanceContext);
        }
    }

    public AbstractPluginDefine constructorPoint(ConstructorInterceptPoint constructorInterceptPoint) {
        EnhanceContext<ConstructorInterceptPoint> enhanceContext = new EnhanceContext<ConstructorInterceptPoint>();
        enhanceContext.setMethodDescription(constructorInterceptPoint.getConstructorMatcher());
        enhanceContext.setInterceptPoint(constructorInterceptPoint);
        binder(enhanceContext);
        return this;
    }

    public AbstractPluginDefine methodPoint(MethodsInterceptPoint methodsInterceptPoint) {
        EnhanceContext<MethodsInterceptPoint> enhanceContext = new EnhanceContext<MethodsInterceptPoint>();
        enhanceContext.setMethodDescription(methodsInterceptPoint.getMethodsMatcher());
        enhanceContext.setInterceptPoint(methodsInterceptPoint);
        binder(enhanceContext);
        return this;
    }

    public AbstractPluginDefine staticMethodPoint(StaticMethodsInterceptPoint staticMethodsInterceptPoint) {
        EnhanceContext<StaticMethodsInterceptPoint> enhanceContext = new EnhanceContext<StaticMethodsInterceptPoint>();
        enhanceContext.setMethodDescription(staticMethodsInterceptPoint.getMethodsMatcher());
        enhanceContext.setInterceptPoint(staticMethodsInterceptPoint);
        binder(enhanceContext);
        return this;
    }


    @Override
    public List<EnhanceContext> enhanceContexts() {
        return enhanceContexts;
    }
}
