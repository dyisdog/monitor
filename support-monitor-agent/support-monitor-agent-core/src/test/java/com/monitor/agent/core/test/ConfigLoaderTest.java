package com.monitor.agent.core.test;

import com.support.monitor.agent.core.config.ConfigLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class ConfigLoaderTest {

    private ConfigLoader configLoader;

    @Before
    public void init() {
        configLoader = new ConfigLoader("E:\\agent.conf");
    }

    @Test
    public void read() {
        log.info("{}", configLoader.getConfig());
    }


}
