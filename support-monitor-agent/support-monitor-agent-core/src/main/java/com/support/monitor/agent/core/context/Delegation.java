package com.support.monitor.agent.core.context;

import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 增强配置块
 *
 * @author 江浩
 */
@Data
@Builder
@ToString
public class Delegation {

    private String tag;

    private ElementMatcher<? super TypeDescription> classDescription;

    private ElementMatcher<? super MethodDescription> methodDescription;

    private Class<? extends AroundInterceptor> interceptorClass;

}
