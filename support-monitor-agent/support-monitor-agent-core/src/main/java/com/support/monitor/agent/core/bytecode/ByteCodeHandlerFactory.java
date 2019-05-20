package com.support.monitor.agent.core.bytecode;

import com.google.inject.Injector;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.plugin.PluginDefine;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.function.Supplier;

/**
 * 字节码处理工厂
 *
 * @author 江浩
 */
public class ByteCodeHandlerFactory implements ByteCodeHandler {

    private final Injector injector;

    private final Instrumentation instrumentation;

    private final AgentConfig agentConfig;

    public ByteCodeHandlerFactory(Injector injector, AgentConfig agentConfig, Instrumentation instrumentation) {
        this.injector = injector;
        this.instrumentation = instrumentation;
        this.agentConfig = agentConfig;
    }

    @Override
    public void handle(Supplier<List<PluginDefine>> supplier) {
        this.handle(supplier.get());
    }

    @Override
    public void handle(List<PluginDefine> pluginDefines) {

    }
}
