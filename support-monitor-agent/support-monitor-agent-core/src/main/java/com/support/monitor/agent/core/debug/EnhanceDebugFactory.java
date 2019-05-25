package com.support.monitor.agent.core.debug;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

import java.io.File;

/**
 * @author
 */
public interface EnhanceDebugFactory {

    void autoWrite(TypeDescription typeDescription, DynamicType dynamicType);


    void fileWrite(File file
            , TypeDescription typeDescription, DynamicType dynamicType);

    void infoPrint(TypeDescription typeDescription, DynamicType dynamicType);

}
