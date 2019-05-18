package com.support.monitor.agent.collect;

import com.support.monitor.agent.collect.config.AgentConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.lang.instrument.Instrumentation;

/**
 * 探针搜集器
 *
 * @author 江浩
 */
@Slf4j
public class AgentCollect {


    private static ClassLoader defaultClassLoader() {
        return AgentCollect.class.getClassLoader();
    }

    /**
     * agent 拦截
     *
     * @param agentArgs
     * @param instrumentation
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {

        ClassLoader defaultClassLoader = defaultClassLoader();

        log.info("agent args: {}", agentArgs);

        log.info("classLoader: {}", defaultClassLoader);

        AgentConfig agentConfig = new AgentConfig(agentArgs);

        try {
            AgentBootStarter agentBootStarter = new AgentBootStarter(defaultClassLoader, agentConfig, instrumentation);
            agentBootStarter.init();
        } catch (Exception e) {
            log.error("agent init error: {}", ExceptionUtils.getMessage(e));
        }

    }

}
