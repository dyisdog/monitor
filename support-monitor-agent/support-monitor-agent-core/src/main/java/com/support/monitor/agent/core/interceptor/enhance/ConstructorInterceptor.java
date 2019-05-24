package com.support.monitor.agent.core.interceptor.enhance;

/**
 * 增强构造器
 * TODO
 *
 * @author 江浩
 */
public interface ConstructorInterceptor extends Interceptor {

    void onConstruct(EnhancedDefine enhancedDefine, Object[] allArguments);
}
