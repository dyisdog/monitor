package com.support.monitor.plugins.okhttp.interceptor;

import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptContext;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;

/**
 * ok http 拦截器
 *
 * @author 江浩
 */
public class OkHttpRealCallMethodInterceptor extends AbstractMethodAroundInterceptor {

    public OkHttpRealCallMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void after(InterceptContext interceptContext) {
        Object result = interceptContext.getResult();
        super.after(interceptContext);
    }
}
