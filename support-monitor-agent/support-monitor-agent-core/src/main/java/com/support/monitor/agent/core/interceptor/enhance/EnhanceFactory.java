package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.EnhanceContext;
import net.bytebuddy.dynamic.DynamicType;

import java.util.List;

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
     * @return : net.bytebuddy.dynamic.DynamicType.Builder<?>
     * @author 江浩
     */
    DynamicType.Builder<?> enhance(DynamicType.Builder<?> builder, List<EnhanceContext> enhanceContexts);

}
