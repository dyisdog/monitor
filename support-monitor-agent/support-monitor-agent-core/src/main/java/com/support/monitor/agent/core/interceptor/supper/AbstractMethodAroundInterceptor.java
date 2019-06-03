package com.support.monitor.agent.core.interceptor.supper;

import com.alipay.common.tracer.core.SofaTracer;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.MethodAroundInterceptor;
import io.opentracing.tag.Tags;
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
    public void before(Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        try {

            SofaTracerSpan sofaTracerSpan = traceContext.getCurrentSpan();

            if (Objects.isNull(sofaTracerSpan)) {
                SofaTracer sofaTracer = getTraceContext().getSofaTracer();
                sofaTracerSpan = (SofaTracerSpan) sofaTracer.buildSpan(this.getClass().getSimpleName())
                        .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER).start();
            } else {
                //构建新的span
                sofaTracerSpan = (SofaTracerSpan) getTraceContext().getSofaTracer()
                        .buildSpan(this.getClass().getSimpleName())
                        .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER)
                        .asChildOf(sofaTracerSpan)
                        .start();
            }
            sofaTracerSpan.setBaggageItem("className", target.getClass().getSimpleName());
            sofaTracerSpan.setBaggageItem("methodName", method.getName());

            getTraceContext().push(sofaTracerSpan);
            this.doBefore(sofaTracerSpan, target, method, allArguments, parameterTypes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * do before
     *
     * @param sofaTracerSpan :
     * @param target         :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @return : void
     * @author 江浩
     */
    protected abstract void doBefore(SofaTracerSpan sofaTracerSpan, Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes);

    @Override
    public void after(Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        SofaTracerSpan sofaTracerSpan = traceContext.stopCurrentTracerSpan();
        this.doAfter(sofaTracerSpan, target, method, allArguments, parameterTypes, result);
    }

    /**
     * do after
     *
     * @param sofaTracerSpan :
     * @param target         :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param result         :
     * @return : void
     * @author 江浩
     */
    protected abstract void doAfter(SofaTracerSpan sofaTracerSpan, Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result);

    @Override
    public void exception(Object target, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }

    protected void print(SofaTracerSpan sofaTracerSpan, Object target, Method method) {
        //sofaTracerSpan.finish();
        //发送 finish目前还没集成

        sofaTracerSpan.finish();

//        System.out.println("threadId: " + Thread.currentThread().getId()
//                + "\t className:  " + enhancedDefine.getClass().getSimpleName()
//                + "\t methodName: " + method.getName()
//                + "\t traceId: " + sofaTracerSpan.getSofaTracerSpanContext().getTraceId()
//                + "\t preSpanId: " + sofaTracerSpan.getSofaTracerSpanContext().getParentId()
//                + "\t spanId: " + sofaTracerSpan.getSofaTracerSpanContext().getSpanId()
//                + "\t startTime: " + sofaTracerSpan.getStartTime()
//                + "\t endTime：" + sofaTracerSpan.getEndTime()
//        );
    }


}
