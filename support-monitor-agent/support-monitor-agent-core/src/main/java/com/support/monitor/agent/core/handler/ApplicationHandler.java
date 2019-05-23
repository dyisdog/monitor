package com.support.monitor.agent.core.handler;

import com.support.monitor.agent.core.context.InitContextAware;

/**
 * 字节码处理
 *
 * @author 江浩
 */
public interface ApplicationHandler extends InitContextAware {

    /**
     * handle
     *
     * @return : void
     * @author 江浩
     */
    void handle();

//    /**
//     * 具体的处理方式
//     *
//     * @param supplier :
//     * @return : void
//     * @author 江浩
//     */
//    default void handle(Supplier<List<PluginDefine>> supplier) {
//        this.handle(supplier.get());
//    }
//
//    /**
//     * 处理方式
//     *
//     * @param pluginDefines
//     */
//    default void handle(List<PluginDefine> pluginDefines) {
//    }

}
