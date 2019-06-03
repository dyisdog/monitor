package com.support.monitor.agent.core.context;

import com.alipay.common.tracer.core.SofaTracer;
import com.alipay.common.tracer.core.context.trace.SofaTraceContext;

/**
 * trace 上线文
 *
 * @author 江浩
 */
public interface TraceContext extends SofaTraceContext {

    /**
     * sofaTracer
     *
     * @return : com.alipay.common.tracer.core.SofaTracer
     * @author 江浩
     */
    SofaTracer getSofaTracer();

}
