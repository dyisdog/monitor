package com.support.monitor.agent.core.interceptor.enhance.rule;

import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.interceptor.enhance.StaticMethodsInterceptor;
import net.bytebuddy.dynamic.DynamicType;

/**
 * 静态方法增强
 *
 * @author 江浩
 */
public class EnhanceStaticMethodRule extends AbstractEnhanceRule<StaticMethodsInterceptor> {

    @Override
    protected DynamicType.Builder<?> enhanceDefine(DynamicType.Builder<?> builder, StaticMethodsInterceptor interceptPoint, EnhanceContext enhanceContext) {

        //TODO
        return null;
    }
}
