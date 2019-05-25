package com.support.monitor.agent.core.interceptor;


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
    Object newInterceptorObject(String className);


}
