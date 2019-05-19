package com.support.monitor.agent.core.plugin.request;

/**
 * header 操作
 *
 * @author 江浩
 */
public interface ClientHeaderAdaptor<H> {

    /**
     * 设置header 信息
     *
     * @param header
     * @param name
     * @param value
     */
    void setHeader(H header, String name, String value);


}