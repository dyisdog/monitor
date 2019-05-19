package com.support.monitor.agent.core.module;

import com.google.inject.Module;
import com.google.inject.util.Modules;

/**
 * core 模块核心模块管理
 *
 * @author 江浩
 */
public class ApplicationContextModuleFactory implements ModuleFactory {

    @Override
    public Module newModule() {
        final Module byteCode = new ByteCodeModule();
        final Module config = new ConfigModule();
        final Module plugin = new PluginModule();
        return Modules.combine(byteCode, config, plugin);
    }

}