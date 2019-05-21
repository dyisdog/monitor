package com.support.monitor.agent.core.interceptor;

import com.support.monitor.agent.core.context.trace.TraceContext;

/**
 * 拦截器参数设置
 *
 * @author 江浩
 */
public interface AroundInterceptorAware {

    void aware(TraceContext traceContext);
}
