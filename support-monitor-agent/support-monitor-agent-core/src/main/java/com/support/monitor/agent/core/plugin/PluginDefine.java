package com.support.monitor.agent.core.plugin;

import com.support.monitor.agent.core.context.EnhanceContext;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import java.util.List;

/**
 * 插件定义
 *
 * @author 江浩
 */
public interface PluginDefine {

    /**
     * 增强拦截类
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
