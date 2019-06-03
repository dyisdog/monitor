package com.support.monitor.agent.core.module.provider;

import com.alipay.common.tracer.core.SofaTracer;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.Reporter;

/**
 * sofaTrace默认构建
 *
 * @author 江浩
 */
public class SofaTracerProvider implements Provider<SofaTracer> {

    private AgentConfig agentConfig;

    private Reporter reporter;

    @Inject
    public SofaTracerProvider(AgentConfig agentConfig, Reporter reporter) {
        this.agentConfig = agentConfig;
        this.reporter = reporter;
    }

    @Override
    public SofaTracer get() {
        return new SofaTracer.Builder(agentConfig.getTracerType())
                .withClientReporter(reporter)
                .withServerReporter(reporter)
                .build();
    }
}
