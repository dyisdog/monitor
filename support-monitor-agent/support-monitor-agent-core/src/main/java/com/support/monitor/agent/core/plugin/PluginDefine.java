package com.support.monitor.agent.core.plugin;

import com.support.monitor.agent.core.context.EnhanceContext;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import java.util.List;
import java.util.UUID;

/**
 * 插件定义
 *
 * @author 江浩
 */
public interface PluginDefine {

    default String name() {
        return "plugin: " + UUID.randomUUID().toString();
    }

    /**
     * 增强拦截类
     * TODO 需要改造判断名字是否加载过
     *
     * @return : net.bytebuddy.matcher.ElementMatcher<? super net.bytebuddy.description.type.TypeDescription>
     * @author 江浩
     */
    ElementMatcher<? super TypeDescription> classDescription();

    /**
     * 增强内容
     *
     * @return : java.util.List<com.support.monitor.agent.core.context.EnhanceContext>
     * @author 江浩
     */
    List<EnhanceContext> enhanceContexts();


}
