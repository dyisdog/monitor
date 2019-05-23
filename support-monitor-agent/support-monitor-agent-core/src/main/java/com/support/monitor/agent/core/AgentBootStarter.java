package com.support.monitor.agent.core;

import com.google.inject.Inject;
import com.support.monitor.agent.core.handler.ApplicationHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 探针启动类
 *
 * @author 江浩
 */
@Slf4j
public class AgentBootStarter {

    private final ApplicationHandler applicationHandler;

    @Inject
    public AgentBootStarter(ApplicationHandler applicationHandler) {
        this.applicationHandler = applicationHandler;
    }


    /**
     * @return : boolean
     * @author 江浩
     */
    public void start() {
        log.info("agent boot starting...");
        applicationHandler.handle();
        log.info("agent boot started...");
    }


}
