package com.support.monitor.agent.collect.test;

import com.support.monitor.agent.collect.AgentBootStarter;
import com.support.monitor.agent.core.config.DefaultAgentConfigLoader;
import org.junit.Test;

public class AgentBootStarterTest {

    @Test
    public void bootStartTest() {
        AgentBootStarter agentBootStarter = new AgentBootStarter(null, new DefaultAgentConfigLoader(""), null);
        agentBootStarter.init();
    }
}
