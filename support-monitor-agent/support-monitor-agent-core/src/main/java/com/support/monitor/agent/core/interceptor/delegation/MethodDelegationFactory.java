package com.support.monitor.agent.core.interceptor.delegation;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import net.bytebuddy.implementation.MethodDelegation;

public interface MethodDelegationFactory {

    /**
     * 默认
     *
     * @param aroundInterceptorClass :
     * @return : net.bytebuddy.implementation.MethodDelegation
     * @author 江浩
     */
    MethodDelegation defaultMethodDelegation(Class<? extends AroundInterceptor> aroundInterceptorClass);

}
