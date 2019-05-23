package com.support.monitor.agent.core.context.trace;

/**
 * TraceContext
 *
 * @author
 */
public interface TraceContext {


    /**
     * return a trace whose sampling rate should be further verified
     *
     * @return
     */
    Trace currentRawTraceObject();

    Trace continueTraceObject(TraceId traceId);

    Trace continueTraceObject(Trace trace);

    Trace newTraceObject();

    /**
     * internal experimental api
     */
    Trace newAsyncTraceObject();

    /**
     * internal experimental api
     */
    Trace continueAsyncTraceObject(TraceId traceId);


}