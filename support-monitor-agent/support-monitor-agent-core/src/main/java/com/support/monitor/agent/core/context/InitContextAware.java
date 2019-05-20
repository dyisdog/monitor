package com.support.monitor.agent.core.context;

import com.google.inject.Injector;
import com.support.monitor.agent.core.config.AgentConfig;

import java.lang.instrument.Instrumentation;

/**
 * 容器初始绑定信息
 *
 * @author 江浩
 */
public interface InitContextAware {


    default void initAware(AgentConfig config, Injector injector, Instrumentation instrumentation) {

    }

}
