package com.support.monitor.agent.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.support.monitor.agent.core.bytecode.ByteBuddyHandler;
import com.support.monitor.agent.core.bytecode.ByteCodeHandler;

/**
 * byte处理
 *
 * @author 江浩
 */
public class ByteCodeModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ByteCodeHandler.class).annotatedWith(Names.named("byteBuddy")).to(ByteBuddyHandler.class).in(Scopes.SINGLETON);
    }
}
