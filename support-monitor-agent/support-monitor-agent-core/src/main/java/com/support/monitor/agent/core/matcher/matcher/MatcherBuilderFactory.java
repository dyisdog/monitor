package com.support.monitor.agent.core.matcher.matcher;

/**
 * 匹配器构建工厂
 *
 * @author 江浩
 */
public class MatcherBuilderFactory {

    /**
     * 过滤匹配器
     *
     * @return : com.support.monitor.agent.core.matcher.matcher.IgnoreMatcher
     * @author 江浩
     */
    public IgnoreMatcher ignoreMatcher() {
        return new IgnoreMatcher();
    }

    /**
     * 类型匹配器
     *
     * @return : com.support.monitor.agent.core.matcher.matcher.TypeMatcher
     * @author 江浩
     */
    public TypeMatcher typeMatcher() {
        return new TypeMatcher();
    }

    /**
     * 方法匹配器
     *
     * @return : com.support.monitor.agent.core.matcher.matcher.MethodMatcher
     * @author 江浩
     */
    public MethodMatcher methodMatcher() {
        return new MethodMatcher();
    }

}


