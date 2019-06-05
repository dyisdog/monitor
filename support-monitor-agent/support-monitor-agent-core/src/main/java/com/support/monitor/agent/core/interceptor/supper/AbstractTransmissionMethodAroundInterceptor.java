package com.support.monitor.agent.core.interceptor.supper;

import com.alipay.common.tracer.core.context.span.SofaTracerSpanContext;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.RemoteTransmission;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class AbstractTransmissionMethodAroundInterceptor<H> extends AbstractMethodAroundInterceptor {

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
        SofaTracerSpanContext spanContext = sofaTracerSpan.getSofaTracerSpanContext();
        remoteTransmission.transmission(remoteHandle, TRANSMISSION_KEY, Objects.isNull(spanContext) ? "" : spanContext.toString());
        super.before(interceptContext);
    }
}
