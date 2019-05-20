package com.support.monitor.agent.collect.test;

import com.support.monitor.agent.core.AgentBootStarter;
import com.support.monitor.agent.core.config.DefaultAgentAgentConfig;
import org.junit.Test;

public class AgentBootStarterTest {

    @Test
    public void bootStartTest() {
        AgentBootStarter agentBootStarter = new AgentBootStarter(null, new DefaultAgentAgentConfig(""), null);
        agentBootStarter.init();
    }
}
