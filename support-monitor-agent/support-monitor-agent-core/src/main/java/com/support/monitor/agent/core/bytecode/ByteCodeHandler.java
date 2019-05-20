package com.support.monitor.agent.core.bytecode;

import com.support.monitor.agent.core.plugin.PluginDefine;

import java.util.List;
import java.util.function.Supplier;

/**
 * 字节码处理
 *
 * @author 江浩
 */
public interface ByteCodeHandler {

    /**
     * 具体的处理方式
     *
     * @param supplier :
     * @return : void
     * @author 江浩
     */
    void handle(Supplier<List<PluginDefine>> supplier);

    void handle(List<PluginDefine> pluginDefines);


}
