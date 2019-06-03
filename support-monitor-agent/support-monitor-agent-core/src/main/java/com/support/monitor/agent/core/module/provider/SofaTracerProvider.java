package com.support.monitor.agent.core.module.provider;

import com.alipay.common.tracer.core.SofaTracer;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.config.AgentConfig;

/**
 * sofaTrace默认构建
 *
 * @author 江浩
 */
public class SofaTracerProvider implements Provider<SofaTracer> {

    private AgentConfig agentConfig;

    @Inject
    public SofaTracerProvider(AgentConfig agentConfig) {
        this.agentConfig = agentConfig;
    }

    @Override
    public SofaTracer get() {
        return new SofaTracer.Builder(agentConfig.getTracerType())
                .withClientReporter(null)
                .withServerReporter(null)
                .build();
    }
}
