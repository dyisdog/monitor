package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.agent.core.context.DefaultReporter;
import com.support.monitor.agent.core.context.Reporter;
import com.support.monitor.commons.binder.plugin.ExtensionLoader;
import com.support.monitor.commons.binder.transfer.TransferDefine;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO 服务端默认传输
 * <p>
 * </p>
 *
 * @author 江浩
 */
@Slf4j
public class ServerReporterProvider implements Provider<Reporter> {

    private AgentConfig agentConfig;

    private ExtensionLoader<TransferDefine> transferDefineExtensions = ExtensionLoader.getExtensionLoader(TransferDefine.class);

    private TransferDefine transferDefine = null;

    @Inject
    public ServerReporterProvider(AgentConfig agentConfig) {
        this.agentConfig = agentConfig;
        this.transferDefine = transferDefineExtensions.getExtension(this.agentConfig.getTransfer());
        log.info("transfer plugin : {},{}", this.agentConfig.getTransfer(), this.transferDefine);
    }

    @Override
    public Reporter get() {
        return new DefaultReporter(this.transferDefine, this.agentConfig);
    }
}
