package com.support.monitor.agent.core.matcher.matcher;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.nameContains;
import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;

/**
 * 全局默认的过滤匹配器
 *
 * @author 江浩
 */
public class GlobalIgnoreMater implements IMatcher<TypeDescription> {

    @Override
    public ElementMatcher<? super TypeDescription> matcher() {
        return nameStartsWith("net.bytebuddy")
                .or(nameStartsWith("org.slf4j"))
                .or(nameStartsWith("org.apache.logging"))
                .or(nameStartsWith("org.groovy"))
                .or(nameContains("javassist"))
                .or(nameContains(".asm."))
                .or(nameStartsWith("sun.reflect"))
                .or(ElementMatchers.<TypeDescription>isSynthetic());
    }
}
