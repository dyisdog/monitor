package com.support.monitor.agent.core.interceptor.callable;

import com.support.monitor.agent.core.interceptor.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

/**
 * @author 江浩
 */
@Slf4j
public class ConstructorInterceptCallable {

    private ConstructorInterceptor constructorInterceptor;

    public ConstructorInterceptCallable(ConstructorInterceptor constructorInterceptor) {
        this.constructorInterceptor = constructorInterceptor;
    }

    @RuntimeType
    public void intercept(@This Object obj,
                          @AllArguments Object[] allArguments) {
        try {
            EnhancedDefine targetObject = (EnhancedDefine) obj;
            constructorInterceptor.onConstruct(targetObject, allArguments);
        } catch (Throwable t) {
            log.error("ConstructorInter failure.", t);
        }

    }
}
