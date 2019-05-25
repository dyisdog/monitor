package com.support.monitor.agent.core.interceptor.supper;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.MethodAroundInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 江浩
 */
@Getter
public abstract class AbstractMethodAroundInterceptor implements MethodAroundInterceptor {

    private TraceContext traceContext;

    public AbstractMethodAroundInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        Trace trace = traceContext.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        trace.traceBegin();
        this.doBefore(trace, enhancedDefine, method, allArguments, parameterTypes);
    }

    /**
     * do before
     *
     * @param trace          :
     * @param enhancedDefine :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @return : void
     * @author 江浩
     */
    protected abstract void doBefore(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes);

    @Override
    public void after(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        Trace trace = traceContext.currentRawTraceObject();
        if (Objects.isNull(trace)) {
            return;
        }
        trace.traceBegin();

        this.doAfter(trace, enhancedDefine, method, allArguments, parameterTypes, result);
    }

    /**
     * do after
     *
     * @param trace          :
     * @param enhancedDefine :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param result         :
     * @return : void
     * @author 江浩
     */
    protected abstract void doAfter(Trace trace, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result);

    @Override
    public void exception(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }

    protected void print(EnhancedDefine enhancedDefine, Method method, Object result, Trace trace) {
        System.out.println(Thread.currentThread().getId() + "  " + enhancedDefine.getClass() + "  "
                + method.getName() + " 执行结果:"
                + result + "  traceId: "
                + trace.currentSpanEventRecorder().getTraceId().id()
                + "  time: " + (trace.currentSpanEventRecorder().getSpan().executeTime()));
    }
}
