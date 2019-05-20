package com.support.monitor.agent.core.bytecode;

import com.support.monitor.agent.core.context.InitContextAware;
import com.support.monitor.agent.core.plugin.PluginDefine;

import java.util.List;
import java.util.function.Supplier;

/**
 * 字节码处理
 *
 * @author 江浩
 */
public interface ByteCodeHandler extends InitContextAware {

    /**
     * 具体的处理方式
     *
     * @param supplier :
     * @return : void
     * @author 江浩
     */
    default void handle(Supplier<List<PluginDefine>> supplier) {
        this.handle(supplier.get());
    }

    /**
     * 处理方式
     *
     * @param pluginDefines
     */
    default void handle(List<PluginDefine> pluginDefines) {
    }

}
