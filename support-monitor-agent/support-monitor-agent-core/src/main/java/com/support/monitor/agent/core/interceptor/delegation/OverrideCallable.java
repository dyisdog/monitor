package com.support.monitor.agent.core.interceptor.delegation;

/**
 * 自定义代理实现方法
 *
 * @author 江浩
 */
public interface OverrideCallable {
    Object call(Object[] args);
}
