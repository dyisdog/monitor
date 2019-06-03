package com.support.monitor.plugins.http.httpclient.interceptor;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractRemoteMethodInterceptor;
import org.apache.http.HttpRequest;

import java.lang.reflect.Method;

/**
 * httpClient写入信息
 *
 * @author 江浩
 */
public class HttpClientMethodInterceptor extends AbstractRemoteMethodInterceptor<HttpRequest> {
    public HttpClientMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    protected void doBefore(SofaTracerSpan sofaTracerSpan, Object enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(SofaTracerSpan sofaTracerSpan, Object enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        this.print(sofaTracerSpan, enhancedDefine, method);
    }
//    public HttpClientMethodInterceptor(TraceContext traceContext) {
//        super(traceContext);
//        //http client default header object
//    }
//
//    @Override
//    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
//        ClientAttach<HttpRequest> httpRequestClientAttach = getClientAttach();
//        //HttpHost target, HttpRequest request
//        HttpRequest httpRequest = null;
//        if (!Objects.isNull(allArguments)) {
//            for (Object object : allArguments) {
//                if (object instanceof HttpRequest) {
//                    httpRequest = (HttpRequest) object;
//                    break;
//                }
//            }
//        }
//        if (Objects.isNull(httpRequest)) {
//            return;
//        }
//
//        attachHandle(trace, new HttpClientHeader(httpRequest));
//    }
//
//
//    @Override
//    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
//        super.print(trace);
//    }
}
