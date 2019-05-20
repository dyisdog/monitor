package com.support.monitor.agent.core.plugin.request;

/**
 * @author 江浩
 */
public interface RequestTraceWriter<T> {

    void write(T header);
}
