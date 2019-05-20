package com.support.monitor.agent.core.plugin.request;

public class DefaultRequestTraceWriter<T> implements RequestTraceWriter<T> {

    private static final String TRACE_ID = "TraceID";


    private ClientHeaderAdaptor<T> clientHeaderAdaptor;

    @Override
    public void write(T header) {
        clientHeaderAdaptor.setHeader(header, TRACE_ID, "");
    }
}
