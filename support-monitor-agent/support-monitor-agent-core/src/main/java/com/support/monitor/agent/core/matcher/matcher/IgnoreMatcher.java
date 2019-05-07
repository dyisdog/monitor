package com.support.monitor.agent.core.matcher.matcher;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 过滤配置匹配器
 *
 * @author 江浩
 */
public class IgnoreMatcher implements IMatcher<TypeDescription> {

    @Override
    public ElementMatcher<? super TypeDescription> matcher() {
        //通过加载配置文件获取
        return new GlobalIgnoreMater().matcher();
    }
}
