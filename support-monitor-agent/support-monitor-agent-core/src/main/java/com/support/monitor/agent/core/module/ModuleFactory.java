package com.support.monitor.agent.core.module;

import com.google.inject.Module;

/**
 * 模块相关信息
 *
 * @author 江浩
 */
public interface ModuleFactory {

    /**
     * 分模块注入
     *
     * @return
     */
    Module newModule();

}
