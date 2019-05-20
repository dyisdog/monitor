package com.support.monitor.agent.core.context;

import com.google.common.collect.Lists;
import com.support.monitor.agent.core.interceptor.PluginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 江浩
 */
@Slf4j
public abstract class AbstractPluginSetupContext implements PluginSetupContext {

    private Map<String, PluginInterceptor> pluginContexts = new ConcurrentHashMap<>();

    public AbstractPluginSetupContext() {
        this.init();
    }

    public void binder(PluginInterceptor pluginInterceptor) {

        String key = pluginInterceptor.name();

        if (StringUtils.isBlank(key)) {
            log.info("binder key is null {}", pluginInterceptor);
            return;
        }

        if (!Objects.isNull(pluginInterceptor) && !pluginContexts.containsKey(pluginInterceptor.name())) {
            pluginContexts.put(pluginInterceptor.name(), pluginInterceptor);
        }
    }

    public void unBinder(String pluginContextTag) {
        if (StringUtils.isNotBlank(pluginContextTag)) {
            pluginContexts.remove(pluginContextTag);
        }
    }

    @Override
    public List<PluginInterceptor> interceptors() {
        return Lists.newArrayList(this.pluginContexts.values());
    }

    /**
     * 初始执行
     *
     * @return : void
     * @author 江浩
     */
    public abstract void init();


}
