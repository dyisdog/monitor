package com.support.monitor.agent.core.interceptor.supper;

import com.alibaba.fastjson.JSONObject;
import com.alipay.common.tracer.core.context.span.SofaTracerSpanContext;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.RemoteTransmission;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author 江浩
 */
@Getter
@Setter
public abstract class AbstractTransmissionMethodAroundInterceptor<H> extends AbstractMethodAroundInterceptor {

    public AbstractTransmissionMethodAroundInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    private H remoteHandle;

    private RemoteTransmission<H> remoteTransmission;

    @Override
    public void before(InterceptContext interceptContext) {
        SofaTracerSpan sofaTracerSpan = getTraceContext().getCurrentSpan();
        if (Objects.isNull(sofaTracerSpan)) {
            return;
        }
        remoteTransmission.transmission(remoteHandle, TRANSMISSION_KEY, sofaTracerSpan.getSofaTracerSpanContext());
        super.before(interceptContext);
    }


    @Override
    public void after(InterceptContext interceptContext, Object result, Throwable throwable) {
        super.after(interceptContext, result, throwable);
    }

    public void nextSofaTracerSpan(H handler) {
        try {
            SofaTracerSpanContext sofaTracerSpanContext = getSpanContextFrom(handler);
            this.nextSofaTracerSpan(sofaTracerSpanContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SofaTracerSpanContext convert(String spanContextJson) {
        if (StringUtils.isBlank(spanContextJson)) {
            return null;
        }
        try {
            return JSONObject.parseObject(spanContextJson, SofaTracerSpanContext.class);
        } catch (Exception e) {
            return null;
        }
    }

    public SofaTracerSpanContext getSpanContextFrom(H handler) {
        return this.remoteTransmission.receive(handler, TRANSMISSION_KEY);
    }
}
