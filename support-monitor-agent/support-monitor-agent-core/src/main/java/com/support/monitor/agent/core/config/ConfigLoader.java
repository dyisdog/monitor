package com.support.monitor.agent.core.config;

import com.typesafe.config.Config;

public interface ConfigLoader {

    Config load(String path);

    Config load();

}
