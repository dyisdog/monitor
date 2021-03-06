package com.support.monitor.plugins.http.interceptor;

import com.alipay.common.tracer.core.context.span.SofaTracerSpanContext;
import com.support.monitor.agent.core.context.RemoteTransmission;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractTransmissionMethodAroundInterceptor;
import org.apache.http.HttpRequest;

/**
 * httpClient写入信息
 *
 * @author 江浩
 */
public class HttpClientMethodInterceptor extends AbstractTransmissionMethodAroundInterceptor<HttpRequest> {

    public HttpClientMethodInterceptor(TraceContext traceContext) {
        super(traceContext);

        setRemoteTransmission(new RemoteTransmission<HttpRequest>() {
            @Override
            public void transmission(HttpRequest handler, String key, SofaTracerSpanContext spanContext) {
                handler.setHeader(key, convert(spanContext));
            }
        });
    }


    @Override
    public void before(InterceptContext interceptContext) {
        HttpRequest httpRequest = getHttpRequest(interceptContext.getArgs());
        super.before(httpRequest, interceptContext);
    }

    private HttpRequest getHttpRequest(Object[] args) {
        if (args != null && args.length >= 1 && args[0] != null && args[0] instanceof HttpRequest) {
            return (HttpRequest) args[0];
        }
        return null;
    }

}
