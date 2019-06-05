package com.support.monitor.agent.core.interceptor.supper;

import com.alibaba.fastjson.JSONObject;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptContext;
import com.support.monitor.agent.core.interceptor.InterceptorPluginAware;
import com.support.monitor.agent.core.interceptor.MethodAroundInterceptor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import static io.opentracing.tag.Tags.SPAN_KIND;
import static io.opentracing.tag.Tags.SPAN_KIND_SERVER;

/**
 * 方法拦截器
 *
 * @author 江浩
 */
@Getter
public abstract class AbstractMethodAroundInterceptor implements MethodAroundInterceptor, InterceptorPluginAware {

    public static final String TRANSMISSION_KEY = "TRANSMISSION_KEY";

    private TraceContext traceContext;

    private String pluginName;

    public AbstractMethodAroundInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }


    @Override
    public void defineName(String pluginName) {
        this.pluginName = pluginName;
    }

    @Override
    public void before(InterceptContext interceptContext) {
        SofaTracerSpan sofaTracerSpan = traceContext.getCurrentSpan();
        this.nextSofaTracerSpan(sofaTracerSpan);
    }


    public SofaTracerSpan nextSofaTracerSpan(SofaTracerSpan preSofaTracerSpan) {
        SofaTracerSpan thisSofaTracerSpan = (SofaTracerSpan) getTraceContext()
                .getSofaTracer()
                .buildSpan(StringUtils.isBlank(pluginName) ? this.getClass().getName() : pluginName)
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
