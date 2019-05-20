package com.support.monitor.agent.core.context;

import com.google.common.collect.Lists;
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

    private Map<String, PluginContext> pluginContexts = new ConcurrentHashMap<>();

    public AbstractPluginSetupContext() {
        this.init();
    }

    public void binder(PluginContext pluginContext) {

        if (!Objects.isNull(pluginContext) && !pluginContexts.containsKey(pluginContext.tag())) {
            pluginContexts.put(pluginContext.tag(), pluginContext);
        }
    }

    public void unBinder(String pluginContextTag) {
        if (StringUtils.isNotBlank(pluginContextTag)) {
            pluginContexts.remove(pluginContextTag);
        }
    }

    @Override
    public List<PluginContext> getPluginContexts() {
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
