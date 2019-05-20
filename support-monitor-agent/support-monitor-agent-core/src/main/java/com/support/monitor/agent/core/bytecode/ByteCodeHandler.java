package com.support.monitor.agent.core.bytecode;

import com.support.monitor.agent.core.plugin.PluginDefine;

import java.lang.instrument.Instrumentation;
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
     * @param instrumentation
     * @param supplier        :
     * @return : void
     * @author 江浩
     */
    default void handle(Instrumentation instrumentation, Supplier<List<PluginDefine>> supplier) {
        this.handle(instrumentation, supplier.get());
    }

    /**
     * 处理方式
     *
     * @param instrumentation
     * @param pluginDefines
     */
    default void handle(Instrumentation instrumentation, List<PluginDefine> pluginDefines) {
    }

}
