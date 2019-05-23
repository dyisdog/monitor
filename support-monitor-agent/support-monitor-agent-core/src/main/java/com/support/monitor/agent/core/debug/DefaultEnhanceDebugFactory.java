package com.support.monitor.agent.core.debug;

import com.support.monitor.agent.core.config.AgentConfig;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

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
    public void fileWrite(TypeDescription typeDescription, DynamicType dynamicType) {
        String debugPath = agentConfig.getDebugPath();

        //native debug
        synchronized (this) {
            if (!StringUtils.isBlank(debugPath)) {
                File classRootPath = new File(debugPath);
                if (classRootPath.exists()) {
                    classRootPath.delete();
                }
                classRootPath.mkdir();

                try {
                    dynamicType.saveIn(classRootPath);
                } catch (IOException e) {
                    log.error("debug class: {} {}", debugPath, e);
                }
            }
        }
    }
}
