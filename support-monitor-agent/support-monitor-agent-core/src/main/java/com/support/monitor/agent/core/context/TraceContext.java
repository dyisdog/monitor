package com.support.monitor.agent.core.context;

import com.alipay.common.tracer.core.SofaTracer;
import com.alipay.common.tracer.core.context.trace.SofaTraceContext;

public interface TraceContext extends SofaTraceContext {

    SofaTracer getSofaTracer();

}
