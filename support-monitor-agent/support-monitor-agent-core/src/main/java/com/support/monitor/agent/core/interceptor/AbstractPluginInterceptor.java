package com.support.monitor.agent.core.interceptor;

import com.support.monitor.agent.core.binder.SpanThreadLocalBinder;
import com.support.monitor.agent.core.binder.TraceThreadLocalBinder;
import com.support.monitor.agent.core.plugin.request.RequestTraceWriter;

/**
 * 插件拦截器
 *
 * @author 江浩
 */
public abstract class AbstractPluginInterceptor<T> implements PluginInterceptor, RequestTraceWriter<T> {

    private static final TraceThreadLocalBinder traceThreadLocalBinder = new TraceThreadLocalBinder();

    private static final SpanThreadLocalBinder spanThreadLocalBinder = new SpanThreadLocalBinder();

    private T header;

}
