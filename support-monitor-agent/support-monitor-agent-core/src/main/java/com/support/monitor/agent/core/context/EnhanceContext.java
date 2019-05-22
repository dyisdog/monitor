package com.support.monitor.agent.core.context;

import com.support.monitor.agent.core.interceptor.ConstructorInterceptPoint;
import com.support.monitor.agent.core.interceptor.MethodsInterceptPoint;
import com.support.monitor.agent.core.interceptor.StaticMethodsInterceptPoint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 增强配置块
 *
 * @author 江浩
 */
@Data
@EqualsAndHashCode
public class EnhanceContext<P> {

    ElementMatcher<? super MethodDescription> methodDescription;

    P interceptPoint;

    public boolean isMethodsInterceptPoint() {
        return interceptPoint instanceof MethodsInterceptPoint;
    }

    public boolean isConstructorInterceptPoint() {
        return interceptPoint instanceof ConstructorInterceptPoint;
    }

    public boolean isStaticMethodsInterceptPoint() {
        return interceptPoint instanceof StaticMethodsInterceptPoint;
    }

}
