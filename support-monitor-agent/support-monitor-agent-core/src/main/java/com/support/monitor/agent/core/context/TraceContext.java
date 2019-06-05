package com.support.monitor.agent.core.context;

import com.alibaba.fastjson.JSONObject;
import com.alipay.common.tracer.core.SofaTracer;
import com.alipay.common.tracer.core.context.trace.SofaTraceContext;
import com.alipay.common.tracer.core.span.SofaTracerSpan;

/**
 * trace 上线文
 *
 * @author 江浩
 */
public interface TraceContext extends SofaTraceContext {

    static final String STOP_OBJECT_KEY = "OBJECT_KEY";

    /**
     * sofaTracer
     *
     * @return : com.alipay.common.tracer.core.SofaTracer
     * @author 江浩
     */
    SofaTracer getSofaTracer();

    /**
     * 停止当前
     *
     * @return : com.alipay.common.tracer.core.span.SofaTracerSpan
     * @author 江浩
     */
    SofaTracerSpan stopCurrentTracerSpan(JSONObject jsonObject);

    SofaTracerSpan stopCurrentTracerSpan();
}
