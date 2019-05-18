package com.support.monitor.agent.core.module;

import com.google.inject.PrivateModule;
import com.google.inject.Scopes;
import net.bytebuddy.agent.builder.AgentBuilder;

/**
 * byte处理
 *
 * @author 江浩
 */
public class ByteCodeModule extends PrivateModule {

    @Override
    protected void configure() {

        bind(AgentBuilder.class).to(AgentBuilder.Default.class).in(Scopes.SINGLETON);
        //暂 TODO
        expose(AgentBuilder.class);
    }
}
