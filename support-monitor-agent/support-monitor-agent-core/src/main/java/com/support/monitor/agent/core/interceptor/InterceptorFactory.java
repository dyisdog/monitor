package com.support.monitor.agent.core.interceptor;


import com.support.monitor.agent.core.plugin.PluginDefine;

/**
 * @author admin
 */
public interface InterceptorFactory {


    /**
     * 创建拦截器实体
     *
     * @param className     :
     * @param pluginDefine:
     * @return : java.lang.Object
     * @author 江浩
     */
    Object newInterceptorObject(String className, PluginDefine pluginDefine);


}
