package com.support.monitor.agent.collect;

import com.support.monitor.agent.core.module.ApplicationContextModuleFactory;
import lombok.extern.slf4j.Slf4j;

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
        ApplicationContextModuleFactory applicationContextModuleFactory = new ApplicationContextModuleFactory();
        applicationContextModuleFactory.load(agentArgs, instrumentation);

    }

}
