package com.support.monitor.agent.core.debug;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

/**
 * @author
 */
public interface EnhanceDebugFactory {

    void fileWrite(TypeDescription typeDescription, DynamicType dynamicType);

}
