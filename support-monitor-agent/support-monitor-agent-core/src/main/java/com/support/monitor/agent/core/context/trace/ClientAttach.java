package com.support.monitor.agent.core.context.trace;

/**
 * 远程Trace写入
 *
 * @author 江浩
 */
public interface ClientAttach<H> {

    /**
     * 设置头信息
     *
     * @param attach :
     * @param key    :
     * @param value  :
     * @return : void
     * @author 江浩
     */
    void setAttach(H attach, String key, String value);


    H getProvider();

}
