package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.DefaultReporter;
import com.support.monitor.agent.core.context.Reporter;

/**
 * TODO 服务端默认传输
 * <p>
 * </p>
 *
 * @author 江浩
 */
public class ServerReporterProvider implements Provider<Reporter> {


    /**
     *
     */
    private AgentConfig agentConfig;

    @Inject
    public ServerReporterProvider(AgentConfig agentConfig) {
        this.agentConfig = agentConfig;
    }

    @Override
    public Reporter get() {
        return new DefaultReporter();
    }
}
