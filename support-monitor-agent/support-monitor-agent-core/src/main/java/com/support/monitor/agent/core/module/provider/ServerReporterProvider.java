package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.DefaultReporter;
import com.support.monitor.agent.core.context.Reporter;

public class ServerReporterProvider implements Provider<Reporter> {

    @Inject
    public ServerReporterProvider() {

    }

    @Override
    public Reporter get() {
        return new DefaultReporter();
    }
}
