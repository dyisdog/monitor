package com.support.monitor.agent.core.context;

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
public class EnhanceContext {

    private ElementMatcher<? super MethodDescription> methodDescription;

    private String interceptorClassName;
}
