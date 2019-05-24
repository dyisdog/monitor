package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.SpanEventRecorder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Objects;

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

        Trace trace = getTraceContext().currentRawTraceObject();
        System.out.println("sync before: " + enhancedDefine.getClass() + "  " + method.getName() + trace);
        if (Objects.isNull(trace)) {
            return;
        }
        SpanEventRecorder spanEventRecorder = trace.currentSpanEventRecorder();
        enhancedDefine.setEnhancedInstanceTraceContext(spanEventRecorder);
    }


    @Override
    public void afterMethod(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        final Trace trace = getTraceContext().currentRawTraceObject();
        if (trace == null) {
            return;
        }
        System.out.println("sync after: " + enhancedDefine.getClass() + " " + method.getName() + " " + trace);
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
