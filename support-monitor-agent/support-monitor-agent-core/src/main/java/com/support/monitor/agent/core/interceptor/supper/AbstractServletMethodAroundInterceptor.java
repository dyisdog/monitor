package com.support.monitor.agent.core.interceptor.supper;

import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;

import java.lang.reflect.Method;

/**
 * servlet
 *
 * @author 江浩
 */
public abstract class AbstractServletMethodAroundInterceptor extends AbstractMethodAroundInterceptor {

    public AbstractServletMethodAroundInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        //super.before(enhancedDefine, method, allArguments, parameterTypes);

    }


    @Override
    public void after(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        super.after(enhancedDefine, method, allArguments, parameterTypes, result);
    }

    @Override
    public void exception(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {
        super.exception(enhancedDefine, method, allArguments, parameterTypes, t);
    }


    //TODO 绑定远程请求的数据信息到本地


}
