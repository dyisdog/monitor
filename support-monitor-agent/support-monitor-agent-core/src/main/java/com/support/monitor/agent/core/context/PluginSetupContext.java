package com.support.monitor.agent.core.context;

import java.util.List;

/**
 * 插件设置
 * <p>
 * 同一个插件可能存在多个不同的处理方式
 * </p>
 *
 * @author 江浩
 */
public interface PluginSetupContext {

    /**
     * 插件名称
     *
     * @return : java.lang.String
     * @author 江浩
     */
    String name();

    List<Delegation> delegations();

}
