package com.support.monitor.agent.core.interceptor;


import com.support.monitor.agent.core.context.EnhanceContext;

/**
 * @author admin
 */
public interface InterceptorFactory {


    /**
     * 创建拦截器实体
     *
     * @param className :
     * @return : java.lang.Object
     * @author 江浩
     */
    Object newInterceptorObject(EnhanceContext className);


}
