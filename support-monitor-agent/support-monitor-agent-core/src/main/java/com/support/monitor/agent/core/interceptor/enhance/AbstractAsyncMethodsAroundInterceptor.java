package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author 江浩
 */
@Slf4j
public abstract class AbstractAsyncMethodsAroundInterceptor extends AbstractMethodsAroundInterceptor {


    public AbstractAsyncMethodsAroundInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void beforeMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

        System.out.println("sync: " + enhancedDefine.getClass() + "  " + method.getName());
    }

    /**
     * 异步Trace处理之后
     *
     * @param trace          :
     * @param enhancedDefine :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @return : void
     * @author 江浩
     */
    protected abstract void doInBeforeMethod(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes);

}
