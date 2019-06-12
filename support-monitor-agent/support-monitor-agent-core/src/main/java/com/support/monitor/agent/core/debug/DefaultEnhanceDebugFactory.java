package com.support.monitor.agent.core.debug;

import com.support.monitor.agent.core.config.AgentConfig;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 江浩
 */
@Slf4j
public class DefaultEnhanceDebugFactory implements EnhanceDebugFactory {

    private AgentConfig agentConfig;

    public DefaultEnhanceDebugFactory(AgentConfig agentConfig) {
        this.agentConfig = agentConfig;
    }

    @Override
    public void autoWrite(TypeDescription typeDescription, DynamicType dynamicType) {
        String debugPath = agentConfig.getDebugPath();
        //native debug
        if (!StringUtils.isBlank(debugPath)) {
            this.fileWrite(new File(debugPath), typeDescription, dynamicType);
        } else {
            this.infoPrint(typeDescription, dynamicType);
        }
    }

    @Override
    public void fileWrite(File file, TypeDescription typeDescription, DynamicType dynamicType) {

        if (Objects.isNull(file)) {
            return;
        }
        synchronized (this) {
            file.deleteOnExit();
            file.mkdir();
            try {
                dynamicType.saveIn(file);
            } catch (IOException e) {
                log.error("debug class: {} {}", file, e);
            }
        }
    }

    @Override
    public void infoPrint(TypeDescription typeDescription, DynamicType dynamicType) {
        log.info("{}\t{}", typeDescription, dynamicType);
    }
}
