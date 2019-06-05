package com.support.monitor.agent.core.interceptor.supper;

import com.alibaba.fastjson.JSONObject;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptContext;
import com.support.monitor.agent.core.interceptor.InterceptorAware;
import com.support.monitor.agent.core.interceptor.MethodAroundInterceptor;
import com.support.monitor.agent.core.plugin.PluginDefine;
import lombok.Getter;

import static io.opentracing.tag.Tags.SPAN_KIND;
import static io.opentracing.tag.Tags.SPAN_KIND_SERVER;

/**
 * 方法拦截器
 *
 * @author 江浩
 */
@Getter
public abstract class AbstractMethodAroundInterceptor implements MethodAroundInterceptor, InterceptorAware {

    public static final String TRANSMISSION_KEY = "TRANSMISSION_KEY";

    private TraceContext traceContext;

    private PluginDefine pluginDefine;

    public AbstractMethodAroundInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void interceptorWithPlugin(PluginDefine pluginDefine) {
        this.pluginDefine = pluginDefine;
    }

    @Override
    public void before(InterceptContext interceptContext) {
        SofaTracerSpan sofaTracerSpan = traceContext.getCurrentSpan();
        this.nextSofaTracerSpan(sofaTracerSpan);
    }


    public SofaTracerSpan nextSofaTracerSpan(SofaTracerSpan preSofaTracerSpan) {
        SofaTracerSpan thisSofaTracerSpan = (SofaTracerSpan) getTraceContext()
                .getSofaTracer()
                .buildSpan(pluginDefine.name())
                .withTag(SPAN_KIND.getKey(), SPAN_KIND_SERVER)
                .asChildOf(preSofaTracerSpan)
                .start();

        getTraceContext().push(thisSofaTracerSpan);

        return thisSofaTracerSpan;
    }


    @Override
    public void after(InterceptContext interceptContext, Object result, Throwable throwable) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("target", interceptContext.getTarget().getClass().getName());
        jsonObject.put("method", interceptContext.getMethod().getName());
        jsonObject.put("args", interceptContext.getArgs());
        jsonObject.put("result", result);
        jsonObject.put("throwable", throwable);
        getTraceContext().stopCurrentTracerSpan(jsonObject);
    }


}
