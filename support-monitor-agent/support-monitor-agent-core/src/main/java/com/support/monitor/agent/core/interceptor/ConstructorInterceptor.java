package com.support.monitor.agent.core.interceptor;

import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

/**
 * 构造器拦截器
 *
 * @author 江浩
 */
public interface ConstructorInterceptor {

    /**
     * 构造器执行
     *
     * @param enhancedDefine :
     * @param allArguments   :
     * @return : void
     * @author 江浩
     */
    void onConstruct(EnhancedDefine enhancedDefine, Object[] allArguments);
}
