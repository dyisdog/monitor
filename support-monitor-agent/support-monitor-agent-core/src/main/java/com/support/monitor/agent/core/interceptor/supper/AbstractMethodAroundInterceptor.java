package com.support.monitor.agent.core.interceptor.supper;

import com.alipay.common.tracer.core.context.span.SofaTracerSpanContext;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptContext;
import com.support.monitor.agent.core.interceptor.InterceptorPluginAware;
import com.support.monitor.agent.core.interceptor.MethodAroundInterceptor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Objects;

import static io.opentracing.tag.Tags.SPAN_KIND;
import static io.opentracing.tag.Tags.SPAN_KIND_SERVER;

/**
 * 方法拦截器
 *
 * @author 江浩
 */
@Getter
@Slf4j
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
        if (Objects.isNull(sofaTracerSpan)) {
            log.info("current span is empty !");
            return;
        }
        this.nextSofaTracerSpan(sofaTracerSpan);
    }


    protected SofaTracerSpan nextSofaTracerSpan(SofaTracerSpan preSofaTracerSpan) {
        SofaTracerSpan thisSofaTracerSpan = (SofaTracerSpan) getTraceContext()
                .getSofaTracer()
                .buildSpan(StringUtils.isBlank(pluginName) ? this.getClass().getName() : pluginName)
                .withTag(SPAN_KIND.getKey(), SPAN_KIND_SERVER)
                .asChildOf(preSofaTracerSpan)
                .start();

        getTraceContext().push(thisSofaTracerSpan);

        return thisSofaTracerSpan;
    }


    public void nextSofaTracerSpan(SofaTracerSpanContext sofaTracerSpanContext) {

        SofaTracerSpan thisSofaTracerSpan = new SofaTracerSpan(getTraceContext().getSofaTracer(), System.currentTimeMillis(), pluginName, sofaTracerSpanContext
                , new HashMap<String, String>(5) {
            {
                put(SPAN_KIND.getKey(), SPAN_KIND_SERVER);
            }
        });

        getTraceContext().push(thisSofaTracerSpan);
    }


    @Override
    public void after(InterceptContext interceptContext, Object result, Throwable throwable) {
        interceptContext.setResult(result);
        interceptContext.setThrowable(throwable);
        getTraceContext().stopCurrentTracerSpan(interceptContext);
    }


}
