package com.support.monitor.agent.core.interceptor.callable;

import com.support.monitor.agent.core.interceptor.enhance.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

@Slf4j
public class ConstructorInterceptCallable {

    private ConstructorInterceptor interceptor;

    public ConstructorInterceptCallable(ConstructorInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @RuntimeType
    public void intercept(@This Object obj,
                          @AllArguments Object[] allArguments) {
        try {
            EnhancedDefine targetObject = (EnhancedDefine) obj;
            interceptor.onConstruct(targetObject, allArguments);
        } catch (Throwable t) {
            log.error("ConstructorInter failure.", t);
        }

    }
}
