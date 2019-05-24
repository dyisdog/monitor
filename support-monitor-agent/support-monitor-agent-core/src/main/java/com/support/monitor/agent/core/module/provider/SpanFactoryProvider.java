package com.support.monitor.agent.core.module.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.id.IdGenerator;
import com.support.monitor.agent.core.context.trace.span.DefaultSpanFactory;
import com.support.monitor.agent.core.context.trace.span.SpanFactory;


/**
 * default
 *
 * @author 江浩
 */
public class SpanFactoryProvider implements Provider<SpanFactory> {

    private IdGenerator idGenerator;

    @Inject
    public SpanFactoryProvider(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }


    @Override
    public SpanFactory get() {
        return new DefaultSpanFactory(this.idGenerator);
    }
}
