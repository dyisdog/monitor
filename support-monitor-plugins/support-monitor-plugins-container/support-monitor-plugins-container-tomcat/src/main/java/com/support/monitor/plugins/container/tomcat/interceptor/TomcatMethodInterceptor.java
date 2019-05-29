package com.support.monitor.plugins.container.tomcat.interceptor;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
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
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) allArguments[0];

        System.out.println(httpServletRequest.getRequestURL());


        //TODO 获取remote或者创建trace
        //attachHandle(trace, new HttpClientHeader(httpRequest));
    }

    @Override
    protected void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

    }

    @Override
    protected void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {

    }
}
