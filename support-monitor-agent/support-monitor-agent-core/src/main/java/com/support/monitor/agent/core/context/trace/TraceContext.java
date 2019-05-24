package com.support.monitor.agent.core.context.trace;

import com.support.monitor.agent.core.context.trace.id.TraceId;

/**
 * TraceContext
 *
 * @author
 */
public interface TraceContext {


    Trace currentRawTraceObject();

    Trace newTraceObject(TraceId traceId);

    Trace newTraceObject(Trace trace);

    Trace newTraceObject();

    Trace newAsyncTraceObject();

    Trace newAsyncTraceObject(TraceId traceId);

}