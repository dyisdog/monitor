package com.support.monitor.agent.core.interceptor.callable;

/**
 * 自定义代理实现方法
 *
 * @author 江浩
 */
public interface OverrideCallable {
    /**
     * invoker
     *
     * @param args :
     * @return : java.lang.Object
     * @author 江浩
     */
    Object invoker(Object[] args);
}
