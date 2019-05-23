package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.config.DefaultAgentConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 江浩
 */
@Slf4j
public class AgentConfigProvider implements Provider<AgentConfig> {

    private String args;

    @Inject
    public AgentConfigProvider(@Named("args") String args) {
        this.args = args;
    }

    @Override
    public AgentConfig get() {
        log.info("load config path: {}", this.args);
        return new DefaultAgentConfig(this.args);
    }
}
