package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.debug.DefaultEnhanceDebugFactory;
import com.support.monitor.agent.core.debug.EnhanceDebugFactory;

/**
 * 调试信息
 * TODO 添加agent输出日志
 *
 * @author 江浩
 */
public class EnhanceDebugFactoryProvider implements Provider<EnhanceDebugFactory> {


    private AgentConfig agentConfig;

    @Inject
    public EnhanceDebugFactoryProvider(AgentConfig agentConfig) {
        this.agentConfig = agentConfig;
    }

    @Override
    public EnhanceDebugFactory get() {
        return new DefaultEnhanceDebugFactory(this.agentConfig);
    }
}
