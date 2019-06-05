package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;
import com.support.monitor.agent.core.interceptor.enhance.rule.DefaultEnhanceRuleChain;
import com.support.monitor.agent.core.interceptor.enhance.rule.EnhanceRule;
import com.support.monitor.agent.core.plugin.PluginDefine;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.dynamic.DynamicType;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 默认method delegation构建工厂
 *
 * @author
 */
@Slf4j
public class DefaultEnhanceFactory implements EnhanceFactory {


    private List<EnhanceRule> enhanceRules;

    private InterceptorFactory interceptorFactory;


    public DefaultEnhanceFactory(List<EnhanceRule> enhanceRules, InterceptorFactory interceptorFactory) {
        this.enhanceRules = enhanceRules;
        this.interceptorFactory = interceptorFactory;
    }


    private DynamicType.Builder<?> enhance(DynamicType.Builder<?> builder, PluginDefine pluginDefine, EnhanceContext enhanceContext) {
        return new DefaultEnhanceRuleChain(this.enhanceRules).enhance(builder, new DefaultEnhanceRuleCallback(enhanceContext, this.interceptorFactory, pluginDefine));
    }

    /**
     * 增强处理
     *
     * @param builder         :
     * @param enhanceContexts :
     * @param index           :
     * @return : net.bytebuddy.dynamic.DynamicType.Builder<?>
     * @author 江浩
     */
    private DynamicType.Builder<?> enhance(DynamicType.Builder<?> builder, PluginDefine pluginDefine, List<EnhanceContext> enhanceContexts, int index) {
        //#to long bug ?
        if (CollectionUtils.isEmpty(enhanceContexts) || index >= enhanceContexts.size()) {
            return builder;
        }
        EnhanceContext enhanceContext = enhanceContexts.get(index);
        builder = this.enhance(builder, pluginDefine, enhanceContext);
        return enhance(builder, pluginDefine, enhanceContexts, ++index);
    }

    @Override
    public DynamicType.Builder<?> enhance(DynamicType.Builder<?> builder, PluginDefine pluginDefine) {

        if (Objects.isNull(pluginDefine)) {
            return builder;
        }
        List<EnhanceContext> enhanceContexts = pluginDefine.enhanceContexts();
        return this.enhance(builder, pluginDefine, enhanceContexts, 0);
    }


    /**
     * callback
     */
    public static class DefaultEnhanceRuleCallback implements EnhanceRule.EnhanceRuleCallback {

        private InterceptorFactory interceptorFactory;

        private EnhanceContext enhanceContext;

        private PluginDefine pluginDefine;


        public DefaultEnhanceRuleCallback(EnhanceContext enhanceContext,
                                          InterceptorFactory interceptorFactory,
                                          PluginDefine pluginDefine) {
            this.interceptorFactory = interceptorFactory;
            this.enhanceContext = enhanceContext;
            this.pluginDefine = pluginDefine;
        }

        @Override
        public InterceptorFactory getInterceptorFactory() {
            return this.interceptorFactory;
        }

        @Override
        public EnhanceContext getEnhanceContext() {
            return this.enhanceContext;
        }

        @Override
        public PluginDefine getPluginDefine() {
            return this.pluginDefine;
        }

    }

}
