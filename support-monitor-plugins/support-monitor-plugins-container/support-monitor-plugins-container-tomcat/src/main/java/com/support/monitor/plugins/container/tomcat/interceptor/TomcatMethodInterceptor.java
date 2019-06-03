package com.support.monitor.plugins.container.tomcat.interceptor;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractServletMethodAroundInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * tomcat 方法执行
 *
 * @author 江浩
 */
public class TomcatMethodInterceptor extends AbstractServletMethodAroundInterceptor {


    public TomcatMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void before(Object enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) allArguments[0];

        System.out.println(httpServletRequest.getRequestURL());


        //TODO 获取remote或者创建trace
        //attachHandle(trace, new HttpClientHeader(httpRequest));
    }

    @Override
    protected void doBefore(SofaTracerSpan sofaTracerSpan, Object enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(SofaTracerSpan sofaTracerSpan, Object enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {

    }

}
