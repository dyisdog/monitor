package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.plugin.PluginDefine;
import net.bytebuddy.dynamic.DynamicType;

/**
 * 增强实现工厂
 *
 * @author
 */
public interface EnhanceFactory {
    /**
     * 增强实现
     *
     * @param builder
     * @param pluginDefine :
     * @return : net.bytebuddy.dynamic.DynamicType.Builder<?>
     * @author 江浩
     */
    DynamicType.Builder<?> enhance(DynamicType.Builder<?> builder, PluginDefine pluginDefine);

}
