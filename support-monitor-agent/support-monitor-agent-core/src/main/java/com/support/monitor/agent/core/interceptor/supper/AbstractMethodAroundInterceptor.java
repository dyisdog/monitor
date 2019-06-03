package com.support.monitor.agent.core.interceptor.supper;

import com.alipay.common.tracer.core.SofaTracer;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.MethodAroundInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
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
    public void before(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes) {
        try {
            SofaTracerSpan sofaTracerSpan = traceContext.getCurrentSpan();

            if (Objects.isNull(sofaTracerSpan)) {
                SofaTracer sofaTracer = getTraceContext().getSofaTracer();
                sofaTracerSpan = (SofaTracerSpan) sofaTracer.buildSpan(this.getClass().getSimpleName())
                        .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER).start();
                getTraceContext().push(sofaTracerSpan);

            } else {
                //构建新的span
                SofaTracerSpan newSofaTracerSpan = (SofaTracerSpan) getTraceContext().getSofaTracer()
                        .buildSpan(this.getClass().getSimpleName())
                        .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_CLIENT)
                        .asChildOf(sofaTracerSpan)
                        .start();
                getTraceContext().push(newSofaTracerSpan);
            }


            this.doBefore(sofaTracerSpan, enhancedDefine, method, allArguments, parameterTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * do before
     *
     * @param sofaTracerSpan :
     * @param enhancedDefine :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @return : void
     * @author 江浩
     */
    protected abstract void doBefore(SofaTracerSpan sofaTracerSpan, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes);

    @Override
    public void after(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result) {
        SofaTracerSpan sofaTracerSpan = traceContext.getCurrentSpan();
        if (Objects.isNull(sofaTracerSpan)) {
            System.out.println("没有span");
            return;
        }

        this.doAfter(sofaTracerSpan, enhancedDefine, method, allArguments, parameterTypes, result);
    }

    /**
     * do after
     *
     * @param sofaTracerSpan :
     * @param enhancedDefine :
     * @param method         :
     * @param allArguments   :
     * @param parameterTypes :
     * @param result         :
     * @return : void
     * @author 江浩
     */
    protected abstract void doAfter(SofaTracerSpan sofaTracerSpan, EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object result);

    @Override
    public void exception(EnhancedDefine enhancedDefine, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }

    protected void print(SofaTracerSpan sofaTracerSpan, EnhancedDefine enhancedDefine, Method method) {
        //sofaTracerSpan.finish();
        //发送 finish目前还没集成

        System.out.println("threadId: " + Thread.currentThread().getId()
                + "\t className:  " + enhancedDefine.getClass().getSimpleName()
                + "\t methodName: " + method.getName()
                + "\t traceId: " + sofaTracerSpan.getSofaTracerSpanContext().getTraceId()
                + "\t preSpanId: " + sofaTracerSpan.getSofaTracerSpanContext().getParentId()
                + "\t spanId: " + sofaTracerSpan.getSofaTracerSpanContext().getSpanId()
                + "\t startTime: " + sofaTracerSpan.getStartTime()
                + "\t endTime：" + sofaTracerSpan.getEndTime()
        );
    }


}
