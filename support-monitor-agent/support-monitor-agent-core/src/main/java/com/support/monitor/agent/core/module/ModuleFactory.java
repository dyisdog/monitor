package com.support.monitor.agent.core.module;

import com.google.inject.Module;

import java.lang.instrument.Instrumentation;

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
    Module initModule();

    /**
     * 模块加载
     */
    void load(String args, Instrumentation instrumentation);


}
