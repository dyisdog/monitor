package com.support.monitor.plugins.http.httpclient.interceptor;

import com.support.monitor.agent.core.context.trace.ClientAttach;
import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.supper.AbstractRemoteMethodInterceptor;
import com.support.monitor.plugins.http.httpclient.HttpClientHeader;
import org.apache.http.HttpRequest;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * httpClient写入信息
 *
 * @author 江浩
 */
public class HttpClientMethodInterceptor extends AbstractRemoteMethodInterceptor<HttpRequest> {
    public HttpClientMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
        //http client default header object
    }

    @Override
    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        ClientAttach<HttpRequest> httpRequestClientAttach = getClientAttach();
        //HttpHost target, HttpRequest request
        HttpRequest httpRequest = null;
        if (!Objects.isNull(allArguments)) {
            for (Object object : allArguments) {
                if (object instanceof HttpRequest) {
                    httpRequest = (HttpRequest) object;
                    break;
                }
            }
        }
        if (Objects.isNull(httpRequest)) {
            return;
        }

        attachHandle(trace, new HttpClientHeader(httpRequest));
    }


    @Override
    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        super.print(trace);
    }
}
