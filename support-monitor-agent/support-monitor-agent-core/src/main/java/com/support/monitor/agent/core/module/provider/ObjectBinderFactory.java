package com.support.monitor.agent.core.module.provider;

import com.google.inject.Provider;
import com.support.monitor.agent.core.context.trace.TraceContext;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ObjectBinderFactory {

    Provider<TraceContext> traceContextProvider;
}
