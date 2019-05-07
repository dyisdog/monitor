package com.support.monitor.agent.core.matcher.matcher;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * 类型匹配器
 *
 * @author 江浩
 */
public class TypeMatcher implements IMatcher<TypeDescription> {

    @Override
    public ElementMatcher<? super TypeDescription> matcher() {
        return ElementMatchers
                .nameStartsWithIgnoreCase("com.example.demoes")
                .and(nameEndsWith("Controller"))
                .and(not(isStatic()))
                .and(not(isInterface()))
                ;
    }
}
