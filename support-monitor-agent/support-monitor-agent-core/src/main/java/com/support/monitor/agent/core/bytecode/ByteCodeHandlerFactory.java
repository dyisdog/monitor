package com.support.monitor.agent.core.bytecode;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.plugin.PluginDefine;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.Objects;

/**
 * 字节码处理工厂
 *
 * @author 江浩
 */
@Slf4j
public class ByteCodeHandlerFactory implements ByteCodeHandler {

    private final Injector injector;

    private final AgentConfig agentConfig;

    private ByteCodeHandler byteCodeHandler;

    public ByteCodeHandlerFactory(Injector injector, AgentConfig agentConfig) {
        this.injector = injector;
        this.agentConfig = agentConfig;
        String byteCodeType = this.agentConfig.getByteCodeType();
        log.info("plugin bytecode type={}", byteCodeType);
        this.byteCodeHandler = this.injector.getInstance(Key.get(ByteCodeHandler.class, Names.named(byteCodeType)));
    }

    @Override
    public void handle(Instrumentation instrumentation, List<PluginDefine> pluginDefines) {
        if (Objects.isNull(pluginDefines)) {
            log.info("handing plugin size:{}", 0);
            return;
        }
        log.info("handing plugin size:{}", pluginDefines.size());
        this.byteCodeHandler.handle(instrumentation, pluginDefines);
    }
}
