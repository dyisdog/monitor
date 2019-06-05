package com.support.monitor.commons.binder.transfer;


import com.typesafe.config.Config;

/**
 * 连接对象
 *
 * @author 江浩
 */
public interface TransferClient {

    /**
     * 服务连接
     *
     * @param config :
     * @return : void
     * @author 江浩
     */
    void connection(Config config);

    /**
     * 断开服务连接
     *
     * @return : void
     * @author 江浩
     */
    void disConnection();


}
